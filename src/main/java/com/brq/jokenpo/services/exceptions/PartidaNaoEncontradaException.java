package com.brq.jokenpo.services.exceptions;

public class PartidaNaoEncontradaException extends RuntimeException{

    private static final long serialVersionUID = 8512204290286562036L;

    public PartidaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PartidaNaoEncontradaException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
