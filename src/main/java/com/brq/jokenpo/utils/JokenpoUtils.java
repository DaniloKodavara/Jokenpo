package com.brq.jokenpo.utils;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Partida;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JokenpoUtils {

    private List<Jogador> ganhadores;

    private List<Jogador> perdedores;

    private List<Jogador> empatados;

    private List<Jogador> jogadoresDeSinalPedra;
    private List<Jogador> jogadoresDeSinalPapel;
    private List<Jogador> jogadoresDeSinalTesoura;
    private List<Jogador> jogadoresDeSinalSpock;
    private List<Jogador> jogadoresDeSinalLagarto;




    public String verificaResultado(Partida partida){


        for (Jogador jogador: partida.getJogadores()) {
                separarJogadores(jogador);
        }

        return null;
    }

    public void separarJogadores(Jogador jogador){
        if(jogador.getSinal().toString() == "PEDRA"){
            jogadoresDeSinalPedra.add(jogador);
        }
        if(jogador.getSinal().toString() == "PAPEL"){
            jogadoresDeSinalPapel.add(jogador);
        }
        if(jogador.getSinal().toString() == "TESOURA"){
            jogadoresDeSinalTesoura.add(jogador);
        }
        if(jogador.getSinal().toString() == "SPOCK"){
            jogadoresDeSinalSpock.add(jogador);
        }
        if(jogador.getSinal().toString() == "LAGARTO"){
            jogadoresDeSinalLagarto.add(jogador);
        }
    }


}
