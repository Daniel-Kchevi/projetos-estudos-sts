package com.jogo.jogadoresjokenpo.resouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogo.jogadoresjokenpo.domain.Jogador;
import com.jogo.jogadoresjokenpo.domain.Partida;
import com.jogo.jogadoresjokenpo.services.ServiceJogada;

@RestController
@RequestMapping("/jogadas")
public class ResourceJogadasController {
	
	@Autowired
	private ServiceJogada serviceJogada;
	
	@PostMapping
	public ResponseEntity<Partida> iniciaJogada(@RequestBody List<Jogador> participantes){
		
		Partida partida = serviceJogada.iniciarPartida(participantes);
		return ResponseEntity.status(HttpStatus.OK).body(partida);
	}

}
