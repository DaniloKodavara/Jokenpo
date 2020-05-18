package com.brq.jokenpo.services.exceptions;

public class MovimentoExistenteException extends RuntimeException{

    private static final long serialVersionUID = -320539612877808898L;

    public MovimentoExistenteException(String mensagem){
        super(mensagem);
    }

    public MovimentoExistenteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
