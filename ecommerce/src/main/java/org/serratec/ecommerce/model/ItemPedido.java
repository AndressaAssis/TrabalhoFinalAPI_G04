package org.serratec.ecommerce.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Jogo jogo;
    
    @Column(name = "precounitario")
    private double precoUnitario;
    
    private int quantidade;
    
    @Column(name = "percentualdesconto")
    private double percentualDesconto;
    
    @Column(name = "valorliquido")
    private double valorLiquido;
    
    @Column(name = "valorbruto")
    private double valorBruto;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
    
	public double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, jogo, pedido, percentualDesconto, precoUnitario, quantidade, valorBruto, valorLiquido);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id) && Objects.equals(jogo, other.jogo) && Objects.equals(pedido, other.pedido)
				&& Double.doubleToLongBits(percentualDesconto) == Double.doubleToLongBits(other.percentualDesconto)
				&& Double.doubleToLongBits(precoUnitario) == Double.doubleToLongBits(other.precoUnitario)
				&& quantidade == other.quantidade
				&& Double.doubleToLongBits(valorBruto) == Double.doubleToLongBits(other.valorBruto)
				&& Double.doubleToLongBits(valorLiquido) == Double.doubleToLongBits(other.valorLiquido);
	}
 
}
