package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.JogoDto;
import org.serratec.ecommerce.exception.ResourceNotFoundException;
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
        List<JogoDto> jogos = jogoService.obterTodos();
        if (jogos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum jogo encontrado.");
        }
        return jogos;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um jogo pelo id",
    description = "Dado um determinado número de id, será retornado o jogo com as informações gerais do genero, preço, quantidade e nome.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o jogo pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Jogo localizado.")		
    })
    public ResponseEntity<JogoDto> obterPorId(@PathVariable Long id) {
        try {
            Optional<JogoDto> dto = jogoService.obterPorId(id);
            if (dto.isEmpty()) {
                throw new ResourceNotFoundException("Não foi encontrado o jogo pelo id informado: " + id);
            }
            return ResponseEntity.ok(dto.get());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao buscar o jogo: " + e.getMessage());
        }
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
        try {
            return jogoService.salvarJogo(dto);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Alguma informação foi passada errada: " + e.getMessage());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao cadastrar jogo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um jogo pelo id",
    description = "Dado um determinado número de id, ele irá excluir o jogo do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Jogo não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Jogo excluído.")		
    })
    public ResponseEntity<Void> excluirJogo(@PathVariable Long id) {
        try {
            boolean jogoApagado = jogoService.apagarJogo(id);
            if (!jogoApagado) {
                throw new ResourceNotFoundException("Jogo não localizado com o id: " + id);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao tentar excluir o jogo: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza o jogo pelo id", description = "Dado um determinado número de id, ele irá modificar o jogo pelo id correspondente.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Jogo não localizado. Verifique!"),
	@ApiResponse(responseCode = "200", description = "Jogo atualizado.") })
    public ResponseEntity<JogoDto> alterarJogo(@PathVariable Long id, @RequestBody JogoDto dto) {
        try {
            Optional<JogoDto> jogoAlterado = jogoService.alterarJogo(id, dto);
            if (jogoAlterado.isEmpty()) {
                throw new ResourceNotFoundException("Jogo não localizado com o id: " + id);
            }
            return ResponseEntity.ok(jogoAlterado.get());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao atualizar o jogo: " + e.getMessage());
        }
    }
}