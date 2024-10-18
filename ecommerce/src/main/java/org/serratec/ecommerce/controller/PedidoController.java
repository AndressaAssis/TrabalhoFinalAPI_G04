package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.PedidoDto;
import org.serratec.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDto> obterTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
        Optional<PedidoDto> dto = pedidoService.obterPorId(id);
        if (!dto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDto cadastrarPedido(@RequestBody PedidoDto dto) {
        return pedidoService.salvarPedido(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        if (!pedidoService.apagarPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto dto) {
        Optional<PedidoDto> pedidoAlterado = pedidoService.alterarPedido(id, dto);
        if (!pedidoAlterado.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoAlterado.get());
    }
}