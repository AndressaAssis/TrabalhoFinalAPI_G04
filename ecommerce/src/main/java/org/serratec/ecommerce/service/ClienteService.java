package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.ClienteDto;
import org.serratec.ecommerce.model.Cliente;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClienteDto> obterTodos() {
		return clienteRepository.findAll().stream().map(ClienteDto::toDTO).toList();
	}

	public Optional<ClienteDto> obterPorId(Long id) {
		if (!clienteRepository.existsById(id)) {
			return Optional.empty();
		}
		return Optional.of(ClienteDto.toDTO(clienteRepository.findById(id).get()));
	}

	public ClienteDto salvarCliente(ClienteDto dto) {
		Cliente clienteEntity = dto.toEntity();
		clienteEntity = clienteRepository.save(clienteEntity);
		return ClienteDto.toDTO(clienteEntity);
	}

	public boolean apagarCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}

	public Optional<ClienteDto> alterarCliente(Long id, ClienteDto dto) {
		if (!clienteRepository.existsById(id)) {
			return Optional.empty();
		}
		Cliente clienteEntity = dto.toEntity();
		clienteEntity.setId(id);
		clienteEntity = clienteRepository.save(clienteEntity);
		return Optional.of(ClienteDto.toDTO(clienteEntity));
	}
}