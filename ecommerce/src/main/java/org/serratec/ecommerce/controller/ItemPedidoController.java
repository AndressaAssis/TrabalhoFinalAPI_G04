package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.ecommerce.dto.ItemPedidoDto;
import org.serratec.ecommerce.dto.ItemPedidoResponseDto;
import org.serratec.ecommerce.exception.ResourceNotFoundException;
import org.serratec.ecommerce.service.ItemPedidoService;
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
@RequestMapping("/itensPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@GetMapping
	@Operation(summary = "Retorna a lista de todos os pedidos da lista",
	    description = "Dado a informação será listado todos o carrinho de pedidos.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum pedido ao carrinho."),
	        @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado.")
	})
	public List<ItemPedidoResponseDto> listarTodos() {
	    List<ItemPedidoDto> itens;

	    try {
	        itens = itemPedidoService.listarTodos();
	    } catch (Exception e) {
	        throw new ResourceNotFoundException("Erro ao buscar os pedidos: " + e.getMessage());
	    }

	    if (itens.isEmpty()) {
	        throw new ResourceNotFoundException("Nenhum pedido encontrado no carrinho.");
	    }

	    return itens.stream()
	            .map(item -> new ItemPedidoResponseDto(
	                item.id(),
	                item.pedidoId(),
	                item.jogoId(),
	                item.precoUnitario(),
	                item.quantidade(),
	                item.percentualDesconto(),
	                item.valorBruto(),
	                item.valorLiquido()
	            ))
	            .collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um carrinho de pedido pelo id",
    description = "Dado um determinado número de id, será retornado o carrinho do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o carrinho pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Carrinho localizado.")		
    })
	public ItemPedidoDto obterPorId(@PathVariable Long id) {
	    try {
	        Optional<ItemPedidoDto> itemPedido = itemPedidoService.obterPorId(id);
	        if (itemPedido.isEmpty()) {
	            throw new ResourceNotFoundException("Não foi encontrado o carrinho pelo id informado: " + id);
	        }
	        return itemPedido.get();
	    } catch (Exception e) {
	        throw new ResourceNotFoundException("Erro ao obter o carrinho: " + e.getMessage());
	    }
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Cadastro de um novo carrinho",
    description = "Função de criação de um novo carrinho.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Alguma informação foi passada errada, Verifique!"),
    		@ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso.")		
    })
	public ItemPedidoDto criarItemPedido(@RequestBody ItemPedidoDto dto) {
	    try {
	        // Validação do dto
	        if (dto == null) {
	            throw new ResourceNotFoundException("Informações inválidas fornecidas para criar o carrinho: objeto não pode ser nulo.");
	        }
	        if (dto.pedidoId() == null || dto.jogoId() == null) {
	            throw new ResourceNotFoundException("Informações inválidas fornecidas para criar o carrinho: 'pedidoId' e 'jogoId' são obrigatórios.");
	        }

	        return itemPedidoService.salvarItemPedido(dto);
	    } catch (Exception e) {
	        throw new ResourceNotFoundException("Erro ao criar o carrinho: " + e.getMessage());
	    }
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um carinho pelo id",
    description = "Dado um determinado número de id, ele irá excluir o carrinho do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Carrinho não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Carrinho excluído.")		
    })
	public ResponseEntity<Void> apagarItemPedido(@PathVariable Long id) {
	    try {
	        // Validação do ID
	        if (id == null || id <= 0) {
	            throw new ResourceNotFoundException("ID inválido fornecido para excluir o carrinho: " + id);
	        }

	        boolean apagado = itemPedidoService.apagarItemPedido(id);
	        if (!apagado) {
	            throw new ResourceNotFoundException("Carrinho não localizado para o id: " + id);
	        }
	        return ResponseEntity.noContent().build();
	    } catch (Exception e) {
	        throw new ResourceNotFoundException("Erro ao tentar excluir o carrinho: " + e.getMessage());
	    }
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um carrinho pelo id",
    description = "Dado um determinado número de id, ele irá modificar o carrinho do cliente pelo id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Carrinho não localizado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Carrinho atualizado.")		
    })
	public ResponseEntity<ItemPedidoDto> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDto dto) {
	    try {
	        // Validação do ID
	        if (id == null || id <= 0) {
	            throw new ResourceNotFoundException("ID inválido fornecido para atualizar o carrinho: " + id);
	        }

	        // Validação do dto
	        if (dto == null) {
	            throw new ResourceNotFoundException("Informações inválidas fornecidas para atualizar o carrinho: objeto não pode ser nulo.");
	        }
	        if (dto.pedidoId() == null || dto.jogoId() == null) {
	            throw new ResourceNotFoundException("Informações inválidas fornecidas para atualizar o carrinho: 'pedidoId' e 'jogoId' são obrigatórios.");
	        }

	        Optional<ItemPedidoDto> itemPedidoAtualizado = itemPedidoService.alterarItemPedido(id, dto);
	        if (itemPedidoAtualizado.isEmpty()) {
	            throw new ResourceNotFoundException("Carrinho não localizado para o id: " + id);
	        }
	        return ResponseEntity.ok(itemPedidoAtualizado.get());
	    } catch (Exception e) {
	        throw new ResourceNotFoundException("Erro ao atualizar o carrinho: " + e.getMessage());
	    }
	}

	@GetMapping("/pedido/{pedidoId}")
	@Operation(summary = "Retorna a lista do carrinho de acordo com o pedido.",
    description = "Dado a informação será retornado o carrinho de acordo com o pedido do id.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum pedido ao carrinho.")		
    })
	public List<ItemPedidoDto> buscarPorPedido(@PathVariable Long pedidoId) {
	    // Validação do pedidoId
	    if (pedidoId == null || pedidoId <= 0) {
	        throw new ResourceNotFoundException("ID do pedido inválido fornecido: " + pedidoId);
	    }

	    List<ItemPedidoDto> itens = itemPedidoService.buscarPorPedido(pedidoId);
	    if (itens.isEmpty()) {
	        throw new ResourceNotFoundException("Nenhum item encontrado para o pedido com id: " + pedidoId);
	    }
	    return itens;
	}

	@GetMapping("/jogo/{jogoId}")
	@Operation(summary = "Retorna a lista do carrinho de acordo com o jogo.",
    description = "Dado a informação será retornado o carrinho que tem todos os jogos referentes ao id.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum jogo referente ao carrinho.")		
    })
	public List<ItemPedidoDto> buscarPorJogo(@PathVariable Long jogoId) {
	    // Validação do jogoId
	    if (jogoId == null || jogoId <= 0) {
	        throw new ResourceNotFoundException("ID do jogo inválido fornecido: " + jogoId);
	    }

	    List<ItemPedidoDto> itens = itemPedidoService.buscarPorJogo(jogoId);
	    if (itens.isEmpty()) {
	        throw new ResourceNotFoundException("Nenhum item encontrado para o jogo com id: " + jogoId);
	    }
	    return itens;
	}
}