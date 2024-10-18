package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.model.Endereco;

public record EnderecoDto(Long id, 
		String cep, 
		String logradouro, 
		String bairro, 
		String cidade,
		double numero,
		String complemento,
		String uf
		){

	public Endereco toEntity() {
		Endereco endereco = new Endereco();
		endereco.setId(this.id);
		endereco.setCep(this.cep);
		endereco.setLogradouro(this.logradouro);
		endereco.setBairro(this.bairro);
		endereco.setCidade(this.cidade);
		endereco.setNumero(this.numero);
		endereco.setComplemento(this.complemento);
		endereco.setUf(this.uf);
		return endereco;
	}

	public static EnderecoDto toDTO(Endereco endereco) {
		return new EnderecoDto(
				endereco.getId(), 
				endereco.getCep(), 
				endereco.getLogradouro(), 
				endereco.getBairro(),
				endereco.getCidade(), 
				endereco.getNumero(), 
				endereco.getComplemento(), 
				endereco.getUf());
	}
}