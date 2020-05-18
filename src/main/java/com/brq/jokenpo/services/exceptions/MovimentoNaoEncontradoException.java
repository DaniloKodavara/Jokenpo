package com.brq.jokenpo.services.exceptions;

public class MovimentoNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 7783159351911281353L;

    public MovimentoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public MovimentoNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
