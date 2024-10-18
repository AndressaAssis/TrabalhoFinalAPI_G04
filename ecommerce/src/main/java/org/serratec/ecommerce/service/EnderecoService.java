package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.EnderecoDto;
import org.serratec.ecommerce.model.Endereco;
import org.serratec.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDto> obterTodos() {
        return enderecoRepository.findAll().stream().map(EnderecoDto::toDTO).toList();
    }

    public Optional<EnderecoDto> obterPorId(Long id) {
        if (!enderecoRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(EnderecoDto.toDTO(enderecoRepository.findById(id).get()));
    }

    public EnderecoDto salvarEndereco(EnderecoDto dto) {
        Endereco enderecoEntity = dto.toEntity();
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        return EnderecoDto.toDTO(enderecoEntity);
    }

    public boolean apagarEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            return false;
        }
        enderecoRepository.deleteById(id);
        return true;
    }

    public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto dto) {
        if (!enderecoRepository.existsById(id)) {
            return Optional.empty();
        }
        Endereco enderecoEntity = dto.toEntity();
        enderecoEntity.setId(id);
        enderecoEntity = enderecoRepository.save(enderecoEntity);
        return Optional.of(EnderecoDto.toDTO(enderecoEntity));
    }
}