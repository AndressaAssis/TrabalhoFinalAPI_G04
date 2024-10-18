package org.serratec.ecommerce.dto;

import java.time.LocalDate;

import org.serratec.ecommerce.model.Genero;
import org.serratec.ecommerce.model.Jogo;
import org.serratec.ecommerce.model.Plataforma;

public record JogoDto(
        Long id,
        String nome,
        Genero genero,
        Plataforma plataforma,
        double precoUnitario,
        String descricao,
        int quantidadeEstoque,
        LocalDate dataCadastro
        ){

    public Jogo toEntity() {
        Jogo jogo = new Jogo();
        jogo.setId(this.id);
        jogo.setNome(this.nome);
        jogo.setGenero(this.genero);
        jogo.setPlataforma(this.plataforma);
        jogo.setPrecoUnitario(this.precoUnitario);
        jogo.setDescricao(this.descricao);
        jogo.setQuantidadeEstoque(this.quantidadeEstoque);
        jogo.setDataCadastro(this.dataCadastro);
        return jogo;
    }

    public static JogoDto toDto(Jogo jogo) {
        return new JogoDto(
                jogo.getId(),
                jogo.getNome(),
                jogo.getGenero(),
                jogo.getPlataforma(),
                jogo.getPrecoUnitario(),
                jogo.getDescricao(),
                jogo.getQuantidadeEstoque(),
                jogo.getDataCadastro());
    }
}
