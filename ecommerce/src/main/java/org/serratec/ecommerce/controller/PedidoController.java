package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.PedidoDto;
import org.serratec.ecommerce.exception.ResourceNotFoundException;
import org.serratec.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Retorna a lista de todos os pedidos",
    description = "Dado a informação será listado todos os pedidos feitos.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não houve nenhum pedido.")		
    })
    public List<PedidoDto> obterTodos() {
        try {
            List<PedidoDto> pedidos = pedidoService.listarTodos();
            if (pedidos.isEmpty()) {
                throw new ResourceNotFoundException("Nenhum pedido encontrado.");
            }
            return pedidos;
        } catch (ResourceNotFoundException e) {
            
            throw e;
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao listar os pedidos.", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um pedido pelo id",
    description = "Dado um determinado número de id, será retornado o pedido com as informações gerais do cliente, o jogo e o valor.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o pedido pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Pedido localizado.")		
    })
    public PedidoDto obterPorId(@PathVariable Long id) {
        try {
            Optional<PedidoDto> dto = pedidoService.obterPorId(id);
            if (!dto.isPresent()) {
                throw new ResourceNotFoundException("Não foi encontrado o pedido com id: " + id);
            }
            return dto.get();
        } catch (ResourceNotFoundException e) {
            
            throw e;
        } catch (Exception e) {
            
            throw new RuntimeException("Erro ao obter o pedido com id: " + id, e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastro de um novo pedido",
    description = "Função de criação de um novo pedido.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Alguma informação foi passada errada, Verifique!"),
    		@ApiResponse(responseCode = "201", description = "Pedido criado com sucesso.")		
    })
    public PedidoDto cadastrarPedido(@RequestBody PedidoDto dto) {
        try {
            
            PedidoDto savedPedido = pedidoService.salvarPedido(dto);
            return savedPedido;
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Alguma informação foi passada errada: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar o pedido.", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pedido pelo id",
    description = "Dado um determinado número de id, ele irá excluir o pedido do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Pedido não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Pedido excluído.")		
    })
    public void excluirPedido(@PathVariable Long id) {
        try {
            boolean pedidoDeletado = pedidoService.apagarPedido(id);
            if (!pedidoDeletado) {
                throw new ResourceNotFoundException("Pedido não localizado com id: " + id);
            }
        } catch (ResourceNotFoundException e) {
            throw e; 
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o pedido com id: " + id, e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pedido pelo id",
    description = "Dado um determinado número de id, ele irá modificar o pedido do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Pedido não localizado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Pedido atualizado.")		
    })
    public PedidoDto alterarPedido(@PathVariable Long id, @RequestBody PedidoDto dto) {
        try {
            Optional<PedidoDto> pedidoAlterado = pedidoService.alterarPedido(id, dto);
            if (!pedidoAlterado.isPresent()) {
                throw new ResourceNotFoundException("Pedido não localizado com id: " + id);
            }
            return pedidoAlterado.get();
        } catch (ResourceNotFoundException e) {
            throw e; 
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar o pedido com id: " + id, e);
        }
    }
}