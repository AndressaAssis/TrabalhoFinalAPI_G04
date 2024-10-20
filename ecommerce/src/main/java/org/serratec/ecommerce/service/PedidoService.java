package org.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.ItemPedidoDto;
import org.serratec.ecommerce.dto.PedidoDto;
import org.serratec.ecommerce.model.Cliente;
import org.serratec.ecommerce.model.ItemPedido;
import org.serratec.ecommerce.model.Jogo;
import org.serratec.ecommerce.model.Pedido;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.serratec.ecommerce.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private JogoRepository jogoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<PedidoDto> listarTodos() {
        return pedidoRepository.findAll().stream().map(PedidoDto::toDTO).toList();
    }

    public Optional<PedidoDto> obterPorId(Long id) {
        if (!pedidoRepository.existsById(id)) {
            return Optional.empty();
        }
		return Optional.of(PedidoDto.toDTO(pedidoRepository.findById(id).get()));
    }

    public PedidoDto salvarPedido(PedidoDto dto) {
    	 Pedido pedido = dto.toEntity();
    	    
    	 Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() ->
    	      new IllegalArgumentException("Cliente não encontrado"));
    	  
    	  pedido.setCliente(cliente);
    	  pedido = pedidoRepository.save(pedido);
    	  
    	  List<ItemPedido> itens = new ArrayList<>();
    	  
    	  for (ItemPedidoDto itemDto : dto.itensPedido()) {
              ItemPedido item = itemDto.toEntity();
              
              item.setPedido(pedido);

              Jogo jogo = jogoRepository.findById(itemDto.jogoId()).orElse(null);
              new IllegalArgumentException("Jogo não encontrado");
              item.setJogo(jogo);
              

              item.setPrecoUnitario(jogo.getPrecoUnitario());
              double valorBruto = jogo.getPrecoUnitario() * item.getQuantidade();
              double valorLiquido = valorBruto - (valorBruto * item.getPercentualDesconto() / 100);
              item.setValorBruto(valorBruto);
              item.setValorLiquido(valorLiquido);
              
              itens.add(item);
          }
             
              pedido.setItensPedido(itens);
              pedidoRepository.save(pedido);
              
    	     return PedidoDto.toDTO(pedido);  
    	 }
    	  

    public boolean apagarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            return false;
        }
        pedidoRepository.deleteById(id);
        return true;
    }

    public Optional<PedidoDto> alterarPedido(Long id, PedidoDto dto) {
        if (!pedidoRepository.existsById(id)) {
            return Optional.empty();
        }
        Pedido pedidoExistente = pedidoRepository.findById(id).get();
        
        Pedido pedidoAtualizado = dto.toEntity();
        pedidoAtualizado.setId(id);
        
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElse(null);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        pedidoAtualizado.setCliente(cliente);
        
        pedidoAtualizado.setItensPedido(new ArrayList<>());
        for (ItemPedidoDto itemDto : dto.itensPedido()) {
            ItemPedido itemPedido = itemDto.toEntity();

            Jogo jogo = jogoRepository.findById(itemDto.jogoId()).orElse(null);
            if (jogo == null) {
                throw new IllegalArgumentException("Jogo não encontrado");
            }

            itemPedido.setPrecoUnitario(jogo.getPrecoUnitario());
            itemPedido.setPedido(pedidoAtualizado);
            itemPedido.calcularValores();

            pedidoAtualizado.getItensPedido().add(itemPedido);
        }
        
        pedidoAtualizado = pedidoRepository.save(pedidoAtualizado);
        return Optional.of(PedidoDto.toDTO(pedidoAtualizado));
    }
}