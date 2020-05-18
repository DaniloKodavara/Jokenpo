package com.brq.jokenpo.services.impl;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.repository.MovimentosRepository;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentoService;
import com.brq.jokenpo.services.exceptions.JogadorNaoEncontradoException;
import com.brq.jokenpo.services.exceptions.MovimentoExistenteException;
import com.brq.jokenpo.services.exceptions.MovimentoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimentoServiceImpl implements MovimentoService {

    @Autowired
    private JogadoresService jogadoresService;

    @Autowired
    private MovimentosRepository movimentosRepository;

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public List<Movimento> listar(){
        return movimentosRepository.findAll();
    }

    public Movimento salvar(Long jogadorId, Movimento movimento) {
        Jogador jogador = jogadoresService.buscar(jogadorId);
        if(!Objects.isNull(jogador.getMovimento())){
            throw new MovimentoExistenteException("O Jogador ja possui movimento");
        }
        movimento.setJogador(jogador);
        EnumMovimento mov = EnumMovimento.getEnumMovementoPorNome(movimento.getMovimento());
        movimento.setEnumMovimento(mov);
        movimento.setMovimento(mov.getNome());
        return movimentosRepository.save(movimento);
    }

    public Movimento buscar(Long id) {
        Optional<Jogador> jogadorOptional = jogadoresRepository.findById(id);
        if (!jogadorOptional.isPresent()) {
            throw new JogadorNaoEncontradoException("O jogador não pode ser encontrado");
        }
        Jogador jogador = jogadorOptional.get();
        if (Objects.isNull(jogador.getMovimento())) {
            throw new MovimentoNaoEncontradoException("O movimento não pode ser encontrado");
        }
        return jogador.getMovimento();
    }

    public void atualizar(Long jogadorId, Movimento movimento) {
        Jogador jogador = jogadoresService.buscar(jogadorId);
        verificarExistencia(jogador.getMovimento());
        EnumMovimento mov = EnumMovimento.getEnumMovementoPorNome(movimento.getMovimento());
        movimento.setId(jogador.getMovimento().getId());
        movimento.setEnumMovimento(mov);
        movimento.setMovimento(mov.getNome());
        movimentosRepository.save(movimento);
    }

    public void deletar(Long jogadorId) {
        Jogador jogador = jogadoresService.buscar(jogadorId);
        verificarExistencia(jogador.getMovimento());
        movimentosRepository.deleteById(jogador.getMovimento().getId());
    }

    public void verificarExistencia(Movimento movimento) {
        buscar(movimento.getId());
    }

    public void limpar() {
        movimentosRepository.deleteAll();
    }
}
