package org.serratec.ecommerce.dto;

public class ItemPedidoResponseDto {
	private Long id;
    private Long pedidoId;
    private Long jogoId;
    private double precoUnitario;
    private int quantidade;
    private double percentualDesconto;
    private double valorBruto;
    private double valorLiquido;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getJogoId() {
		return jogoId;
	}
	public void setJogoId(Long jogoId) {
		this.jogoId = jogoId;
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
	public double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	
	public ItemPedidoResponseDto(Long id, Long pedidoId, Long jogoId, double precoUnitario,
			int quantidade, double percentualDesconto, double valorBruto, double valorLiquido) {
		super();
		this.id = id;
		this.pedidoId = pedidoId;
		this.jogoId = jogoId;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
	}
	
    
    
}
