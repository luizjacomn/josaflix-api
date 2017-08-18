package com.jjrjlj.josaflix.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por interceptar as exceções lançadas e fazer o tratamento
 * apropriado
 * 
 * @author luiz-jaco
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(FilmeNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleRecursoNaoEncontradoException(FilmeNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErroBuilder().comTitulo("O recurso não pode ser encontrado!").comStatus(404L)
				.comTimestampAtual().criarDetalhesErro();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}