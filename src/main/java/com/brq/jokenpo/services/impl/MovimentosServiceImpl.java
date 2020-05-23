package com.brq.jokenpo.services.impl;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.repository.MovimentosRepository;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentosService;
import com.brq.jokenpo.services.exceptions.JogadorNaoEncontradoException;
import com.brq.jokenpo.services.exceptions.MovimentoExistenteException;
import com.brq.jokenpo.services.exceptions.MovimentoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimentosServiceImpl implements MovimentosService {

    @Autowired
    private JogadoresService jogadoresService;

    @Autowired
    private MovimentosRepository movimentosRepository;

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
        Jogador jogador = jogadoresService.buscar(id);

        Optional<Movimento> movimentoOptional = movimentosRepository.findById(jogador.getMovimento().getId());
        if (!movimentoOptional.isPresent()) {
            throw new MovimentoNaoEncontradoException("O movimento não pode ser encontrado");
        }
        return movimentoOptional.get();
    }

    public void atualizar(Long jogadorId, Movimento movimento) {
        Jogador jogador = jogadoresService.buscar(jogadorId);
        jogador.setId(jogadorId);
        EnumMovimento mov = EnumMovimento.getEnumMovementoPorNome(movimento.getMovimento());
        movimento.setEnumMovimento(mov);
        movimento.setJogador(jogador);
        movimento.setId(jogador.getMovimento().getId());
        movimentosRepository.saveAndFlush(movimento);
    }

    public void verificarExistencia(Movimento movimento) {
        buscar(movimento.getId());
    }

    public void limpar() {
        movimentosRepository.deleteAll();
    }
}
