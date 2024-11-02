package org.serratec.ecommerce.service;

import org.serratec.ecommerce.model.ItemPedido;
import org.serratec.ecommerce.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	public String enviarEmailComPedido(Pedido pedido, String destinatario) {
		SimpleMailMessage enviaMensagem = new SimpleMailMessage();
		
		enviaMensagem.setFrom("andressaassisjandre@gmail.com");
		enviaMensagem.setTo(destinatario);
		enviaMensagem.setSubject("Confirmação do Pedido nº " + pedido.getId());
		
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("---------------------------------------\n")
		        .append("Olá ").append(pedido.getCliente().getNome()).append(", Obrigada por comprar na Green Steam! \n")
		        .append("Espero que tenha gostado da compra!\n")
		        .append("---------------------------------------\n")
		        .append("Número do Pedido: ").append(pedido.getId()).append("\n")
		        .append("Data do Pedido: ").append(pedido.getDataPedido()).append("\n")
		        .append("Valor Total: R$ ").append(pedido.getValorTotal()).append("\n")
		        .append("\nItens do Pedido:\n")
		        .append("---------------------------------------\n");
	
		for (ItemPedido item : pedido.getItensPedido()) {
		    mensagem.append("Jogo: ").append(item.getJogo().getNome()).append("\n")
		            .append("Quantidade: ").append(item.getQuantidade()).append("\n")
		            .append("Preço Unitário: R$ ").append(item.getPrecoUnitario()).append("\n")
		            .append("Desconto: ").append(item.getPercentualDesconto()).append("%\n")
		            .append("Valor Bruto: R$ ").append(item.getValorBruto()).append("\n")
		            .append("Valor Líquido: R$ ").append(item.getValorLiquido()).append("\n")
		            .append("A chave do seu jogo é: ").append(item.getChaveUnica()).append("\n")
		            .append("---------------------------------------\n");
		}

		enviaMensagem.setText(mensagem.toString());
		
		try {
		    sender.send(enviaMensagem);
		    return "E-mail enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar mensagem. Verificar!";
		}
	}

}
