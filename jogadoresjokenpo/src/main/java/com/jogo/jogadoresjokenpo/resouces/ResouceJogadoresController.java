package com.jogo.jogadoresjokenpo.resouces;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jogo.jogadoresjokenpo.domain.Jogador;
import com.jogo.jogadoresjokenpo.services.ServiceJogador;


@RestController
@RequestMapping("/jogadores")
public class ResouceJogadoresController {
	
	@Autowired
	private ServiceJogador serviceJogador;
		
	@GetMapping
	public ResponseEntity<List<Jogador>> listarJogadores() {
		return ResponseEntity.status(HttpStatus.OK).body(serviceJogador.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> cadastrarJogadores(@Valid @RequestBody Jogador jogador){
		jogador = serviceJogador.salvar(jogador);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(jogador.getId())
				.toUri();		
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atulizarJogador(@Valid @RequestBody Jogador jogador, 
			@PathVariable("id") Long jogadorId){
		jogador.setId(jogadorId);
		serviceJogador.atualiza(jogador);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirJogador(@PathVariable("id") Long jogadorId){		
		serviceJogador.excluir(jogadorId);		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogador> listarJogadorPorId(@PathVariable("id") Long jogadorId){
		Jogador jogador = serviceJogador.listarPorId(jogadorId);
		return ResponseEntity.status(HttpStatus.OK).body(jogador);
	}

}
