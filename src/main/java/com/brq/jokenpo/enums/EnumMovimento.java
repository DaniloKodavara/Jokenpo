package com.brq.jokenpo.enums;

import com.brq.jokenpo.services.exceptions.MovimentoInvalidoException;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum EnumMovimento {

    SPOCK("SPOCK"),
    TESOURA("TESOURA"),
    PAPEL("PAPEL"),
    PEDRA("PEDRA"),
    LAGARTO("LAGARTO");

    private String nome;
    private List<EnumMovimento> fraqueza;

    static {
        SPOCK.setFraqueza(asList(LAGARTO, PAPEL));
        TESOURA.setFraqueza(asList(SPOCK, PEDRA));
        PAPEL.setFraqueza(asList(TESOURA, LAGARTO));
        PEDRA.setFraqueza(asList(SPOCK, PAPEL));
        LAGARTO.setFraqueza(asList(TESOURA, PEDRA));
    }

    EnumMovimento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public List<EnumMovimento> getFraqueza() {
        return fraqueza;
    }

    public void setFraqueza(List<EnumMovimento> fraqueza) {
        this.fraqueza = fraqueza;
    }

    public static EnumMovimento getEnumMovementoPorNome(String nome) {
        for (EnumMovimento elemento : Arrays.asList(EnumMovimento.values())) {
            if (nome.equals(elemento.getNome())) {
                return elemento;
            }
        }
        throw new MovimentoInvalidoException("O movimento é invalido");
    }


}
