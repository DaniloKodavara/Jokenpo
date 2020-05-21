package com.brq.jokenpo.domain;

import com.brq.jokenpo.enums.EnumMovimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "JOGADOR_ID")
    @JsonIgnore
    private Jogador jogador;

    private String movimento;

    @JsonIgnore
    private EnumMovimento enumMovimento;

    public Movimento() {
    }

    public Movimento(EnumMovimento enumMovimento){
        this.enumMovimento = enumMovimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }

    public EnumMovimento getEnumMovimento() {
        return enumMovimento;
    }

    public void setEnumMovimento(EnumMovimento enumMovimento) {
        this.enumMovimento = enumMovimento;
    }

}