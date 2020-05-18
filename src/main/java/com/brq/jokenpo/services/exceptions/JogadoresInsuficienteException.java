package com.brq.jokenpo.services.exceptions;

public class JogadoresInsuficienteException extends RuntimeException{
    private static final long serialVersionUID = -1595781260542301973L;

    public JogadoresInsuficienteException(String mensagem){
        super(mensagem);
    }

    public JogadoresInsuficienteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
