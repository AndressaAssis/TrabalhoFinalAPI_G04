package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.CadastroClienteDto;
import org.serratec.ecommerce.dto.ClienteDto;
import org.serratec.ecommerce.service.ClienteService;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@Operation(summary = "Retorna a lista de todos os clientes",
    description = "Dado a informação será listado todos os clientes cadastrados.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Caso a lista retorne vazia, é porque não existe nenhum cliente cadastrado.")		
    })
	public List<ClienteDto> listarTodos() {
		return clienteService.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um cliente pelo id",
    description = "Dado um determinado número de id, será retornado o cliente com as informações gerais do nome, email, cpf e endereço.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Não foi encontrado o cliente pelo id informado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Cliente localizado.")		
    })
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id) {
		Optional<ClienteDto> cliente = clienteService.obterPorId(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Cadastro de um novo cliente",
    description = "Função de criação de um novo cliente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "404", description = "Alguma informação foi passada errada, Verifique!"),
    		@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso.")		
    })
	public ClienteDto criarCliente(@RequestBody CadastroClienteDto dto) {
		return clienteService.salvarCliente(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um cliente pelo id",
    description = "Dado um determinado número de id, ele irá excluir o cliente do id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Cliente não localizado. Verifique!"),
    		@ApiResponse(responseCode = "204", description = "Cliente excluído.")		
    })
	public ResponseEntity<Void> apagarCliente(@PathVariable Long id) {
		if (!clienteService.apagarCliente(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um cliente pelo id",
    description = "Dado um determinado número de id, ele irá modificar o cadastro do cliente pelo id correspondente.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400", description = "Cliente não localizado. Verifique!"),
    		@ApiResponse(responseCode = "200", description = "Cliente atualizado.")		
    })
	public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {
		Optional<ClienteDto> clienteAtualizado = clienteService.alterarCliente(id, dto);
		if (!clienteAtualizado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteAtualizado.get());
	}
}