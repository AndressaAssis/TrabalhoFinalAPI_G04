package org.serratec.ecommerce.dto;

import java.time.LocalDate;

public record CadastroClienteDto(

		Long id, 
		String nome, 
		String cpf, 
		String email,
		LocalDate dataNascimento,
		String cep,
		String numero,
		String complemento)
{

}
