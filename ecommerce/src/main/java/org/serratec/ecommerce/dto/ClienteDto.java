package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.model.Cliente;

public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String numero;
    private String cep;

    public ClienteDto() {}

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.numero = cliente.getEndereco().getNumero();
        this.cep = cliente.getEndereco().getCep();
    }

    public static ClienteDto toDTO(Cliente cliente) {
        return new ClienteDto(cliente);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
