package org.serratec.ecommerce.dto;

import java.util.List;

import org.serratec.ecommerce.model.Cliente;

public record ClienteDto(Long id, 
		String nome, 
		String cpf, 
		String email, 
		List<Long> enderecosIds,
		List<Long> pedidosIds) {

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setEmail(this.email);
		return cliente;
	}

	public static ClienteDto toDTO(Cliente cliente) {
		List<Long> enderecosIds = cliente.getEnderecos().stream().map(endereco -> endereco.getId()).toList();
		List<Long> pedidosIds = cliente.getPedidos().stream().map(pedido -> pedido.getId()).toList();
		return new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), enderecosIds,
				pedidosIds);
	}
}