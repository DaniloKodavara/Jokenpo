package com.brq.jokenpo.services.exceptions;

public class JogadorExistenteException extends RuntimeException{
    private static final long serialVersionUID = 1815445100478917065L;

    public JogadorExistenteException(String mensagem){
        super(mensagem);
    }

    public JogadorExistenteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
