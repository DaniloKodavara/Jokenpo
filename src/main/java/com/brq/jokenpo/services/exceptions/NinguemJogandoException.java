package com.brq.jokenpo.services.exceptions;

public class NinguemJogandoException extends RuntimeException{
    private static final long serialVersionUID = 8992407515448407819L;

    public NinguemJogandoException(String mensagem){
        super(mensagem);
    }

    public NinguemJogandoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
