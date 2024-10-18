package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.JogoDto;
import org.serratec.ecommerce.service.JogoService;
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
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public List<JogoDto> obterTodos() {
        return jogoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoDto> obterPorId(@PathVariable Long id) {
        Optional<JogoDto> dto = jogoService.obterPorId(id);
        if (!dto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogoDto cadastrarJogo(@RequestBody JogoDto dto) {
        return jogoService.salvarJogo(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirJogo(@PathVariable Long id) {
        if (!jogoService.apagarJogo(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoDto> alterarJogo(@PathVariable Long id, @RequestBody JogoDto dto) {
        Optional<JogoDto> jogoAlterado = jogoService.alterarJogo(id, dto);
        if (!jogoAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jogoAlterado.get());
    }
}