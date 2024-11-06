package org.serratec.ecommerce.config;

public class JwtResponse {
    private String token;
    private String nome;

    public JwtResponse(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }

    // Getters e Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
