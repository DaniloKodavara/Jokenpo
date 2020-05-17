package com.brq.jokenpo.services;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Partida;
import com.brq.jokenpo.repository.PartidasRepository;
import com.brq.jokenpo.services.exceptions.PartidaExistenteException;
import com.brq.jokenpo.services.exceptions.PartidaNaoEncontradaException;
import com.brq.jokenpo.utils.JokenpoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidasService {

    @Autowired
    private PartidasRepository partidasRepository;

    @Autowired
    private JokenpoUtils utils;


    public List<Partida> listar() {
        return partidasRepository.findAll();
    }

    public Partida salvar(Partida partida) {

        if (partida.getId() != null) {
            if (partidasRepository.findById(partida.getId()).isPresent()) {
                throw new PartidaExistenteException("A partida já existe");
            }
        }

        partida.setResultado(utils.verificaResultado(partida));
        return partidasRepository.save(partida);
    }


    public Partida buscar(Long id) {
        Optional<Partida> partidaOptional = partidasRepository.findById(id);
        if (!partidaOptional.isPresent()) {
            throw new PartidaNaoEncontradaException("A partida não pode ser encontrada");
        }
        return partidaOptional.get();
    }

    public void atualizar(Partida partida) {
        verificarExistencia(partida);
        partidasRepository.save(partida);
    }

    public void deletar(Long id) {
        try {
            partidasRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PartidaNaoEncontradaException("A partida não pode ser encontrada");
        }
    }

    public void verificarExistencia(Partida partida) {
        buscar(partida.getId());
    }
}
