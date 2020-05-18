package com.brq.jokenpo.services.exceptions;

public class DetectarVencedorException extends RuntimeException{

    public DetectarVencedorException(String mensagem){
        super(mensagem);
    }

    public DetectarVencedorException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
