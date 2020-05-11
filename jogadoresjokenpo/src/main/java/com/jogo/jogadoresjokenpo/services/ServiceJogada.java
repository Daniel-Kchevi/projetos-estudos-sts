package com.jogo.jogadoresjokenpo.services;

import java.util.List;

import com.jogo.jogadoresjokenpo.domain.Jogador;
import com.jogo.jogadoresjokenpo.domain.Partida;

public interface ServiceJogada {
	
	Partida iniciarPartida(List<Jogador> participantes);
	
}
