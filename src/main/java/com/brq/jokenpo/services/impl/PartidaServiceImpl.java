package com.brq.jokenpo.services.impl;

import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.domain.Partida;
import com.brq.jokenpo.enums.EnumMovimento;
import com.brq.jokenpo.repository.PartidasRepository;
import com.brq.jokenpo.services.PartidasService;
import com.brq.jokenpo.services.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaServiceImpl implements PartidasService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartidaServiceImpl.class);

    private static final String NENHUM_GANHADOR = "NINGUEM GANHOU!";
    private static final String UM_GANHADOR = " É O GANHADOR!";
    private static final String MUITOS_GANHADORES = "OS GANHADORES : ";
    private static final String MUITOS_GANHADORES_SEPARADOR = " / ";

    @Autowired
    private PartidasRepository partidasRepository;

    private JogadorServiceImpl jogadorService;
    private MovimentoServiceImpl movimentoService;

    @Autowired
    public PartidaServiceImpl(JogadorServiceImpl jogadorService, MovimentoServiceImpl movimentoService) {
        this.jogadorService = jogadorService;
        this.movimentoService = movimentoService;
    }

    public Partida play() {
        this.checarRequerimentos();
        List<String> vencedores = new ArrayList<>();

        LOGGER.debug("Gerar Resultados");
        this.movimentoService.listar().forEach(obj -> {
            try {
                if (checarGanhador(obj.getEnumMovimento().getFraqueza())) {
                    vencedores.add(obj.getJogador().getNome());
                }
            } catch (DetectarVencedorException e) {
                LOGGER.error("Erro detectando vencedores.",
                        obj.getJogador().getNome(), e.getMessage());
            }
        });
        LOGGER.debug("Resultado gerado");

        Partida resultado = new Partida(this.getMensagemVitoria(vencedores),
                this.getHistoricoDeMovimentos(this.movimentoService.listar()));
        LOGGER.debug("Vencedores mensagem formatada");

        LOGGER.debug("Apagando dados de movimentos");
        this.movimentoService.limpar();

        LOGGER.debug("Rodada Finalizada");

        partidasRepository.save(resultado);

        return resultado;
    }

    private void checarRequerimentos() {
        if (this.jogadorService.listar().size() == 0) {
            throw new NinguemJogandoException("Nenhum jogador encontrado");
        } else if (this.jogadorService.listar().size() <= 1) {
            throw new JogadoresInsuficienteException("Número de jogadores é insuficiente");
        } else if (this.movimentoService.listar().size() <= 1) {
            throw new MovimentosInsuficienteException("Número de movimentos é insuficiente");
        } else if (this.movimentoService.listar().size() != this.jogadorService.listar().size()) {
            throw new JogadoresSemMovimentoException("Ainda faltam jogadores escolher movimento.");
        }
    }

    private Boolean checarGanhador(List<EnumMovimento> fraqueza) throws DetectarVencedorException {
        for (EnumMovimento enumMovimento : fraqueza) {
            LOGGER.debug("Checando fraqueza : {}", enumMovimento.getNome());
            for (Movimento resp : this.movimentoService.listar()) {
                if (resp.getEnumMovimento().getNome().compareTo(enumMovimento.getNome()) == 0) {
                    LOGGER.debug("Perdedor -perdeu para {} - {}", resp.getJogador().getNome(), enumMovimento.getNome());
                    return false;
                }
            }
        }
        LOGGER.debug("Detectou Vencedor");
        return true;
    }

    private String getMensagemVitoria(List<String> vencedores) {
        String menssagem = "";
        if (vencedores.size() == 0) {
            menssagem = NENHUM_GANHADOR;
        } else if (vencedores.size() == 1) {
            menssagem = vencedores.get(0).toUpperCase().trim() + UM_GANHADOR;
        } else {
            menssagem = MUITOS_GANHADORES;
            int counter = 0;
            for (String nome : vencedores) {
                counter++;
                if (counter == vencedores.size()) {
                    menssagem = menssagem + nome;
                } else {
                    menssagem = menssagem + nome + MUITOS_GANHADORES_SEPARADOR;
                }
            }
        }
        return menssagem;
    }

    private List<String> getHistoricoDeMovimentos(List<Movimento> list) {
        List<String> result = new ArrayList<>();
        for (Movimento resp : list) {
            String menssagem = resp.getJogador().getNome() + " (" + resp.getEnumMovimento().getNome() + ")";
            result.add(menssagem);
        }
        return result;
    }
}
