package org.serratec.curriculo.service;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculo.Dto.CandidatoDto;
import org.serratec.curriculo.model.Candidato;
import org.serratec.curriculo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	@Autowired
	private CandidatoRepository repositorio;
	
	public List<CandidatoDto> obterTodos() {
		return repositorio.findAll().stream().map(c -> CandidatoDto.toDto(c)).toList();
	}
	
	public Optional<CandidatoDto> obterPorId(Long id) {
		if(!repositorio.existsById(id) ) {
			return Optional.empty();
		}
		return Optional.of(CandidatoDto.toDto(repositorio.findById(id).get()));
	}
	
	public CandidatoDto salvarCandidato(CandidatoDto candidato) {
		return CandidatoDto.toDto(repositorio.save(candidato.ToEntity()));
	}
	
	public boolean apagarCandidato(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public Optional<CandidatoDto> modificarCandidato (Long id, CandidatoDto candidato) {
		if(!repositorio.existsById(id) ) {
			return Optional.empty();
		}
		Candidato candidatoEntity = candidato.ToEntity();
		candidatoEntity.setId(id);
		repositorio.save(candidatoEntity);
		return Optional.of(CandidatoDto.toDto(candidatoEntity));
	}
	
 }
