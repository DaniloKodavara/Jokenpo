package com.brq.jokenpo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "O campo nome não pode ser vazio.")
    private String nome;

    @OneToOne(mappedBy = "jogador", cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Movimento movimento;

}