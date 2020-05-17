package com.brq.jokenpo.domain;

import com.brq.jokenpo.enums.Sinal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "O campo nome não pode ser vazio.")
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Campo sinal é de preenchimento obrigatório.")
    private Sinal sinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTIDA_ID")
    @JsonIgnore
    private Partida partida;

    public Jogador(){
        super();
    }

    public Jogador(String nome, Sinal sinal) {
        this.nome = nome;
        this.sinal = sinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sinal getSinal() {
        return sinal;
    }

    public void setSinal(Sinal sinal) {
        this.sinal = sinal;
    }


    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
