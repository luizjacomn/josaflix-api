package com.jjrjlj.josaflix.exception;

/**
 * 
 * @author luiz-jaco
 *
 */

public class DetalhesErroBuilder {

	private DetalhesErro erro;

	public DetalhesErroBuilder() {
		this.erro = new DetalhesErro();
	}
	
	public DetalhesErroBuilder comTitulo(String titulo) {
		this.erro.setTitulo(titulo);
		return this;
	}
	
	public DetalhesErroBuilder comStatus(Long codigoStatus) {
		this.erro.setStatus(codigoStatus);
		return this;
	}
	
	public DetalhesErroBuilder comTimestampAtual() {
		this.erro.setTimestamp(System.currentTimeMillis());
		return this;
	}

	public DetalhesErroBuilder comMensagemDesenvolvedor(String mensagem) {
		this.erro.setMensagemDesenvolvedor(mensagem);
		return this;
	}

	public DetalhesErro criarDetalhesErro() {
		return this.erro;
	}

}