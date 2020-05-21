package com.brq.jokenpo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String resultado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ElementCollection
    private List<String> historico;

}