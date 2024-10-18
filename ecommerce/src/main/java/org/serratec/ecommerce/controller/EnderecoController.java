package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.EnderecoDto;
import org.serratec.ecommerce.service.EnderecoService;
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
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDto> listarTodos() {
        return enderecoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> obterPorId(@PathVariable Long id) {
        Optional<EnderecoDto> endereco = enderecoService.obterPorId(id);
        if (!endereco.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDto criarEndereco(@RequestBody EnderecoDto dto) {
        return enderecoService.salvarEndereco(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarEndereco(@PathVariable Long id) {
        if (!enderecoService.apagarEndereco(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDto dto) {
        Optional<EnderecoDto> enderecoAtualizado = enderecoService.alterarEndereco(id, dto);
        if (!enderecoAtualizado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoAtualizado.get());
    }
}