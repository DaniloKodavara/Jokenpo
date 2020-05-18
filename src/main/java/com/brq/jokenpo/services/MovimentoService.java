package com.brq.jokenpo.services;

import com.brq.jokenpo.domain.Movimento;

import java.util.List;

public interface MovimentoService {

    List<Movimento> listar();
    Movimento salvar(Long jogadorId, Movimento movimento);
    Movimento buscar(Long id);
    void atualizar(Long jogadorId, Movimento movimento);
    void deletar(Long jogadorId);
    void verificarExistencia(Movimento movimento);

    void limpar();
}
