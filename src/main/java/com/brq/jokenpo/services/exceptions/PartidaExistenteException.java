package com.brq.jokenpo.services.exceptions;

public class PartidaExistenteException extends RuntimeException{

    private static final long serialVersionUID = 2340620296531832692L;

    public PartidaExistenteException(String mensagem){
        super(mensagem);
    }

    public PartidaExistenteException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
