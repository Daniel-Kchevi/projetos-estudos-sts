package com.jogo.jogadoresjokenpo.services;

import java.util.List;

import com.jogo.jogadoresjokenpo.domain.Jogador;

public interface ServiceJogador {
	
	List<Jogador> listar();
	
	Jogador salvar(Jogador jogador);
	
	Jogador listarPorId(Long jogadorId);
	
	void atualiza(Jogador jogador);

	void excluir(Long jogadorId);
}
