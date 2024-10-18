package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.serratec.ecommerce.dto.JogoDto;
import org.serratec.ecommerce.model.Jogo;
import org.serratec.ecommerce.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repositorio;

    public List<JogoDto> obterTodos() {
        return repositorio.findAll().stream().map(j -> JogoDto.toDto(j)).toList();
    }

    public Optional<JogoDto> obterPorId(Long id) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(JogoDto.toDto(repositorio.findById(id).get()));
    }

    public JogoDto salvarJogo(JogoDto dto) {
        Jogo jogoEntity = repositorio.save(dto.toEntity());
        return JogoDto.toDto(jogoEntity);
    }

    public boolean apagarJogo(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }

    public Optional<JogoDto> alterarJogo(Long id, JogoDto dto) {
        if (!repositorio.existsById(id)) {
            return Optional.empty();
        }
        Jogo jogoEntity = dto.toEntity();
        jogoEntity.setId(id);
        repositorio.save(jogoEntity);
        return Optional.of(JogoDto.toDto(jogoEntity));
    }
}
 