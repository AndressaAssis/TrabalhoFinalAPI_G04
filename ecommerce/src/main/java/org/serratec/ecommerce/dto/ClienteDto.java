package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.serratec.ecommerce.model.Cliente;
import org.serratec.ecommerce.model.Endereco;

public record ClienteDto(
		Long id, 
		String nome, 
		String cpf, 
		String email,
		LocalDate dataNascimento,
		Endereco endereco,
		List<Long> pedidosIds
		){

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setEmail(this.email);
		cliente.setDataNascimento(this.dataNascimento);
		cliente.setEndereco(this.endereco);
		return cliente;
	}

	public static ClienteDto toDTO(Cliente cliente) {
		List<Long> pedidosIds = new ArrayList<Long>();
		
		if (Objects.nonNull(cliente.getPedidos())) {
			pedidosIds = cliente.getPedidos().stream().map(pedido -> pedido.getId()).toList();
		}
		
		return new ClienteDto(cliente.getId(), 
				cliente.getNome(), 
				cliente.getCpf(), 
				cliente.getEmail(), 
				cliente.getDataNascimento(), 
				cliente.getEndereco(),
				pedidosIds);
	}
}