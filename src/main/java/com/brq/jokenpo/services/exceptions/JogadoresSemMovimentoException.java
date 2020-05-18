package com.brq.jokenpo.services.exceptions;

public class JogadoresSemMovimentoException extends RuntimeException{

    public JogadoresSemMovimentoException(String mensagem){
        super(mensagem);
    }

    public JogadoresSemMovimentoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
