package org.serratec.ecommerce.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CadastroClienteDto(
    Long id, 
    String nome, 
    String cpf, 
    String email,
    String senha, // Novo campo de senha
    String numero,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dataNascimento,
    String cep
) {
}
