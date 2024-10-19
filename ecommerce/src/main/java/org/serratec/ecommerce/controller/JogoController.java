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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    @Operation(summary = "Retorna a lista de todos os jogos",
    description = "Dado a informação será listado todos os jogos cadastrados.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum jogo cadastrado.")		
    })
    public List<JogoDto> obterTodos() {
        return jogoService.obterTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um jogo pelo id",
    description = "Dado um determinado número de id, será retornado o jogo com as informações gerais do genero, preço, quantidade e nome.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o jogo pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Jogo localizado.")		
    })
    public ResponseEntity<JogoDto> obterPorId(@PathVariable Long id) {
        Optional<JogoDto> dto = jogoService.obterPorId(id);
        if (!dto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastro de um novo jogo",
    description = "Função de criação de um novo jogo.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Alguma informação foi passada errada, Verifique!"),
    		@ApiResponse(responseCode = "201", description = "Jogo criado com sucesso.")		
    })
    public JogoDto cadastrarJogo(@RequestBody JogoDto dto) {
        return jogoService.salvarJogo(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um jogo pelo id",
    description = "Dado um determinado número de id, ele irá excluir o jogo do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Jogo não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Jogo excluído.")		
    })
    public ResponseEntity<Void> excluirJogo(@PathVariable Long id) {
        if (!jogoService.apagarJogo(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um jogo pelo id",
    description = "Dado um determinado número de id, ele irá modificar o jogo do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Jogo não localizado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Jogo atualizado.")		
    })
    public ResponseEntity<JogoDto> alterarJogo(@PathVariable Long id, @RequestBody JogoDto dto) {
        Optional<JogoDto> jogoAlterado = jogoService.alterarJogo(id, dto);
        if (!jogoAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jogoAlterado.get());
    }
}