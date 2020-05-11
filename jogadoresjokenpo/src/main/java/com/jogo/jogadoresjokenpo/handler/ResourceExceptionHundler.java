package com.jogo.jogadoresjokenpo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jogo.jogadoresjokenpo.domain.ErroDetalhes;
import com.jogo.jogadoresjokenpo.service.exception.JogadorNaoEncontrado;

@ControllerAdvice
public class ResourceExceptionHundler {
	
	@ExceptionHandler(JogadorNaoEncontrado.class)
	public ResponseEntity<ErroDetalhes> handlerJogadorNaoEncontrado(JogadorNaoEncontrado e, 
			HttpServletRequest request){
		
		ErroDetalhes erro = new ErroDetalhes();
		erro.setStatus(404l);
		erro.setTitulo("O jogador não foi encontrado.");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);	
	}

}
