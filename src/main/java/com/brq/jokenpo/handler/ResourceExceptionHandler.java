package com.brq.jokenpo.handler;

import com.brq.jokenpo.domain.DetalhesErro;
import com.brq.jokenpo.services.exceptions.*;
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
        erro.setTitulo("Jogador já existente.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerJogadorNaoEncontradoException(JogadorNaoEncontradoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O jogador não pôde ser encontrado.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(PartidaExistenteException.class)
    public ResponseEntity<DetalhesErro> handlePartidaExistenteException(PartidaExistenteException e, HttpServletRequest request)
    {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Partida já existente.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(PartidaNaoEncontradaException.class)
    public ResponseEntity<DetalhesErro> handlerPartidaNaoExistenteException(PartidaNaoEncontradaException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("A partida não pode ser encontrada.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MovimentoExistenteException.class)
    public ResponseEntity<DetalhesErro> handleMovimentoExistenteException(MovimentoExistenteException e, HttpServletRequest request)
    {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Movimento já existente.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MovimentoInvalidoException.class)
    public ResponseEntity<DetalhesErro> handleMovimentoInvalidoException(MovimentoInvalidoException e, HttpServletRequest request)
    {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("O movimento é invalido.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MovimentoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerMovimentoNaoEncontradoException(MovimentoNaoEncontradoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O movimento não pôde ser encontrado.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição inválida.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DetalhesErro> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição inválida.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(NinguemJogandoException.class)
    public ResponseEntity<DetalhesErro> handlerNinguemJogandoException(NinguemJogandoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Nenhum jogador encontrado.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(JogadoresInsuficienteException.class)
    public ResponseEntity<DetalhesErro> handlerJogadoresInsuficienteException(JogadoresInsuficienteException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Numero de Jogadores é Insuficiente.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MovimentosInsuficienteException.class)
    public ResponseEntity<DetalhesErro> handlerMovimentosInsuficienteException(MovimentosInsuficienteException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Numero de Movimentos é Insuficiente.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(JogadoresSemMovimentoException.class)
    public ResponseEntity<DetalhesErro> handlerJogadoresSemMovimentoException(JogadoresSemMovimentoException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Ainda faltam jogadores escolher movimento.");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DetectarVencedorException.class)
    public ResponseEntity<DetalhesErro> handlerDetectarVencedorException(DetectarVencedorException e, HttpServletRequest request){
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo(".");
        erro.setMensagemDesenvolvedor("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
