package com.brq.jokenpo.handler;

import com.brq.jokenpo.domain.DetalhesErro;
import com.brq.jokenpo.services.exceptions.JogadorExistenteException;
import com.brq.jokenpo.services.exceptions.JogadorNaoEncontradoException;
import com.brq.jokenpo.services.exceptions.PartidaExistenteException;
import com.brq.jokenpo.services.exceptions.PartidaNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(JogadorExistenteException.class)
    public ResponseEntity<DetalhesErro> handleJogadorExistenteException(JogadorExistenteException e, HttpServletRequest request)
    {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Jogador já existente");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerJogadorNaoEncontradoException(JogadorNaoEncontradoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O livro não pôde ser encontrado");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(PartidaExistenteException.class)
    public ResponseEntity<DetalhesErro> handlePartidaExistenteException(PartidaExistenteException e, HttpServletRequest request)
    {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Partida já existente");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(PartidaNaoEncontradaException.class)
    public ResponseEntity<DetalhesErro> handlerPartidaNaoExistenteException(PartidaNaoEncontradaException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("A partida não pode ser encontrada");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição inválida");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DetalhesErro> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição inválida");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
