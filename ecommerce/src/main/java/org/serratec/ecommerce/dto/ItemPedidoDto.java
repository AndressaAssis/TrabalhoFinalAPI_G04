package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.model.ItemPedido;

public record ItemPedidoDto(
		Long id, 
		Long pedidoId, 
		Long jogoId, 
		double precoUnitario, 
		int quantidade,
		double percentualDesconto, 
		double valorLiquido,
		double valorBruto
		){

	public ItemPedido toEntity() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(this.id);
		itemPedido.setPrecoUnitario(this.precoUnitario);
		itemPedido.setQuantidade(this.quantidade);
		itemPedido.setPercentualDesconto(this.percentualDesconto);
		itemPedido.setValorLiquido(this.valorLiquido);
		itemPedido.setValorBruto(this.valorBruto);
		return itemPedido;
	}

	public static ItemPedidoDto toDTO(ItemPedido itemPedido) {
		return new ItemPedidoDto(itemPedido.getId(), itemPedido.getPedido().getId(), itemPedido.getJogo().getId(),
				itemPedido.getPrecoUnitario(), itemPedido.getQuantidade(), itemPedido.getPercentualDesconto(),
				itemPedido.getValorLiquido(), itemPedido.getValorBruto());
	}
}