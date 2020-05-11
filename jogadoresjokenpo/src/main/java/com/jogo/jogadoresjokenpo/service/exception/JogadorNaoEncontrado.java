package com.jogo.jogadoresjokenpo.service.exception;

public class JogadorNaoEncontrado extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public JogadorNaoEncontrado(String mensagem) {
		super();
	}
	
	public JogadorNaoEncontrado(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
