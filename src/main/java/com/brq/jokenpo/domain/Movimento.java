package com.brq.jokenpo.domain;

import com.brq.jokenpo.enums.EnumMovimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOGADOR_ID", referencedColumnName = "id")
    @JsonIgnore
    private Jogador jogador;

    private String movimento;

    @JsonIgnore
    private EnumMovimento enumMovimento;

}