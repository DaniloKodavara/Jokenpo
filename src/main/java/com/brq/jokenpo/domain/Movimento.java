package com.brq.jokenpo.domain;

import com.brq.jokenpo.enums.EnumMovimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

}