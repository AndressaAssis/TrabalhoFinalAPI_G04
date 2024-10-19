package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.ecommerce.dto.ItemPedidoDto;
import org.serratec.ecommerce.dto.ItemPedidoResponseDto;
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
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum pedido ao carrinho.")		
    })
	public List<ItemPedidoResponseDto> listarTodos() {
		return itemPedidoService.listarTodos().stream()
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
	public ResponseEntity<ItemPedidoDto> obterPorId(@PathVariable Long id) {
		Optional<ItemPedidoDto> itemPedido = itemPedidoService.obterPorId(id);
		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedido.get());
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
		return itemPedidoService.salvarItemPedido(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um carinho pelo id",
    description = "Dado um determinado número de id, ele irá excluir o carrinho do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Carrinho não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Carrinho excluído.")		
    })
	public ResponseEntity<Void> apagarItemPedido(@PathVariable Long id) {
		if (!itemPedidoService.apagarItemPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um carrinho pelo id",
    description = "Dado um determinado número de id, ele irá modificar o carrinho do cliente pelo id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Carrinho não localizado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Carrinho atualizado.")		
    })
	public ResponseEntity<ItemPedidoDto> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDto dto) {
		Optional<ItemPedidoDto> itemPedidoAtualizado = itemPedidoService.alterarItemPedido(id, dto);
		if (!itemPedidoAtualizado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedidoAtualizado.get());
	}

	@GetMapping("/pedido/{pedidoId}")
	@Operation(summary = "Retorna a lista do carrinho de acordo com o pedido.",
    description = "Dado a informação será retornado o carrinho de acordo com o pedido do id.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum pedido ao carrinho.")		
    })
	public List<ItemPedidoDto> buscarPorPedido(@PathVariable Long pedidoId) {
		return itemPedidoService.buscarPorPedido(pedidoId);
	}

	@GetMapping("/jogo/{jogoId}")
	@Operation(summary = "Retorna a lista do carrinho de acordo com o jogo.",
    description = "Dado a informação será retornado o carrinho que tem todos os jogos referentes ao id.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum jogo referente ao carrinho.")		
    })
	public List<ItemPedidoDto> buscarPorJogo(@PathVariable Long jogoId) {
		return itemPedidoService.buscarPorJogo(jogoId);
	}
}