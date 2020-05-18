package com.brq.jokenpo.services.impl;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.exceptions.JogadorExistenteException;
import com.brq.jokenpo.services.exceptions.JogadorNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadoresServiceImpl implements JogadoresService {

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public List<Jogador> listar() {
        return jogadoresRepository.findAll();
    }

    public Jogador salvar(Jogador jogador) {
        if (jogador.getId() != null) {
            if (jogadoresRepository.findById(jogador.getId()).isPresent()) {
                throw new JogadorExistenteException("O jogador já existe");
            }
        }
        return jogadoresRepository.save(jogador);
    }

    public Jogador buscar(Long id) {
        Optional<Jogador> jogadorOptional = jogadoresRepository.findById(id);
        if (!jogadorOptional.isPresent()) {
            throw new JogadorNaoEncontradoException("O jogador não pode ser encontrado");
        }
        return jogadorOptional.get();
    }

    public void atualizar(Jogador jogador) {
        verificarExistencia(jogador);
        jogadoresRepository.save(jogador);
    }

    public void deletar(Long id) {
        try {
            jogadoresRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new JogadorNaoEncontradoException("O jogador não pode ser encontrado");
        }
    }

    public void limpar() {
        jogadoresRepository.deleteAll();
    }

    public void verificarExistencia(Jogador jogador) {
        buscar(jogador.getId());
    }
}
