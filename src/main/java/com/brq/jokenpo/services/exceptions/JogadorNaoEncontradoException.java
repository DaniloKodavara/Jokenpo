package com.brq.jokenpo.services.exceptions;

public class JogadorNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 4372371788054301909L;

    public JogadorNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public JogadorNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
