package com.brq.jokenpo.services.exceptions;

public class MovimentosInsuficienteException extends RuntimeException{
    private static final long serialVersionUID = 8211878360567594404L;

    public MovimentosInsuficienteException(String mensagem){
        super(mensagem);
    }

    public MovimentosInsuficienteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
