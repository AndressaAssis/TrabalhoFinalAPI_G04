package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.ecommerce.model.Pedido;

public record PedidoDto(Long id, LocalDate dataPedido, double valorTotal, Long clienteId,
		List<ItemPedidoDto> itensPedido) {

	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(this.dataPedido);
		pedido.setValorTotal(this.valorTotal);
		return pedido;
	}

	public static PedidoDto toDTO(Pedido pedido) {
		return new PedidoDto(pedido.getId(), pedido.getDataPedido(), pedido.getValorTotal(),
				pedido.getCliente().getId(), pedido.getItensPedido().stream().map(ItemPedidoDto::toDTO).toList()

		);
	}
}