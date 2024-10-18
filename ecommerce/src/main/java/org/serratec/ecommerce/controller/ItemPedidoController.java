package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.ItemPedidoDto;
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

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@GetMapping
	public List<ItemPedidoDto> listarTodos() {
		return itemPedidoService.listarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> obterPorId(@PathVariable Long id) {
		Optional<ItemPedidoDto> itemPedido = itemPedidoService.obterPorId(id);
		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedido.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemPedidoDto criarItemPedido(@RequestBody ItemPedidoDto dto) {
		return itemPedidoService.salvarItemPedido(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagarItemPedido(@PathVariable Long id) {
		if (!itemPedidoService.apagarItemPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoDto> atualizarItemPedido(@PathVariable Long id, @RequestBody ItemPedidoDto dto) {
		Optional<ItemPedidoDto> itemPedidoAtualizado = itemPedidoService.alterarItemPedido(id, dto);
		if (!itemPedidoAtualizado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedidoAtualizado.get());
	}

	@GetMapping("/pedido/{pedidoId}")
	public List<ItemPedidoDto> buscarPorPedido(@PathVariable Long pedidoId) {
		return itemPedidoService.buscarPorPedido(pedidoId);
	}

	@GetMapping("/jogo/{jogoId}")
	public List<ItemPedidoDto> buscarPorJogo(@PathVariable Long jogoId) {
		return itemPedidoService.buscarPorJogo(jogoId);
	}
}