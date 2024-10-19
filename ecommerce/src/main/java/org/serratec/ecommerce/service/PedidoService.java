package org.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.PedidoDto;
import org.serratec.ecommerce.model.Cliente;
import org.serratec.ecommerce.model.Pedido;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
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
    	    
    	 Cliente cliente = clienteRepository.findById(dto.clienteId()).orElse(null);
    	 if (cliente == null) {
    	     throw new IllegalArgumentException("Cliente não encontrado");
    	  }
    	  pedido.setCliente(cliente);
    	  pedido.setItensPedido(new ArrayList<>());
    	  
    	  pedidoRepository.save(pedido);
    	  return PedidoDto.toDTO(pedido);
    	}
  //      Pedido pedidoEntity = dto.toEntity();
   //     pedidoEntity = pedidoRepository.save(pedidoEntity);
   //     return PedidoDto.toDTO(pedidoEntity);
   // }

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
        Pedido pedidoEntity = dto.toEntity();
        pedidoEntity.setId(id);
        pedidoEntity = pedidoRepository.save(pedidoEntity);
        return Optional.of(PedidoDto.toDTO(pedidoEntity));
    }
}