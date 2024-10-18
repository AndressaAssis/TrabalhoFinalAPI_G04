package org.serratec.ecommerce.repository;

import java.util.List;

import org.serratec.ecommerce.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	List<ItemPedido> findByPedidoId(Long pedidoId);

	List<ItemPedido> findByJogoId(Long jogoId);

}
