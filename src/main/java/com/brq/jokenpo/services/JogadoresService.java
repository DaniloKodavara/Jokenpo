package com.brq.jokenpo.services;

import com.brq.jokenpo.domain.Jogador;

import java.util.List;

public interface JogadoresService {

    List<Jogador> listar();
    Jogador salvar(Jogador jogador);
    Jogador buscar(Long id);
    void atualizar(Jogador jogador);
    void deletar(Long id);
    void verificarExistencia(Jogador jogador);
    void limpar();

}
