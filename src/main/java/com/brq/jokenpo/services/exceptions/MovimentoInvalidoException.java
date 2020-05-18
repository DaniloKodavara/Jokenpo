package com.brq.jokenpo.services.exceptions;

public class MovimentoInvalidoException extends RuntimeException{

    private static final long serialVersionUID = -1619197112351419381L;

    public MovimentoInvalidoException(String mensagem){
        super(mensagem);
    }

    public MovimentoInvalidoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
