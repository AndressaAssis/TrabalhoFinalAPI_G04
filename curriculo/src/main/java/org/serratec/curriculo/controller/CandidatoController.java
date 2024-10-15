package org.serratec.curriculo.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.curriculo.Dto.CandidatoDto;
import org.serratec.curriculo.model.VagaDesejada;
import org.serratec.curriculo.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
	@Autowired
	private CandidatoService servico;
	
	@GetMapping
	public List<CandidatoDto> obterTodos() {
		return servico.obterTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CandidatoDto> obterPorId(@PathVariable Long id) {
		Optional<CandidatoDto> candidato = servico.obterPorId(id);
		
		if(candidato.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidato.get());
	}
	
	@GetMapping("/vaga/{vaga}")
	public List<CandidatoDto> obterPorVaga(@PathVariable VagaDesejada vaga) {
		return servico.obterPorVaga(vaga);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CandidatoDto cadastrarCandidato(@RequestBody CandidatoDto candidato) {
		return servico.salvarCandidato(candidato);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
		if(!servico.apagarCandidato(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CandidatoDto> alterarCandidato(@PathVariable Long id, @RequestBody CandidatoDto candidato) {
		Optional<CandidatoDto> candidatoAlterado = servico.modificarCandidato(id, candidato);
		if(candidatoAlterado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatoAlterado.get());
	}
}

