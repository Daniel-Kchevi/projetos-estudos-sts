package com.jogo.jogadoresjokenpo.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jogo.jogadoresjokenpo.domain.Jogada;
import com.jogo.jogadoresjokenpo.domain.Jogador;
import com.jogo.jogadoresjokenpo.domain.Partida;
import com.jogo.jogadoresjokenpo.services.ServiceJogada;
import com.jogo.jogadoresjokenpo.utils.UtilsJogadas;

@Service
public class JogadaServiceImpl implements ServiceJogada{

	public Partida iniciarPartida(List<Jogador> participantes) {
		Partida partida = jogar(participantes);
		return partida;
	}

	/**
	 * @param participantes
	 * @return uma partida com os participante, jogadores e o vencedor
	 */
	private Partida jogar(List<Jogador> participantes) {	
		Partida partida = new Partida();
		List<Jogador> jogadores = new ArrayList<>();
		Long contador = 1L;

		for(Jogador jogador: participantes) {
			Jogada jogada = realizaJogada(jogador, contador);
			jogador.setJogada(jogada);
			jogadores.add(jogador);
			contador++;
		}

		partida.setJogadores(jogadores);
		partida.setVencedor(vencedorPartida(jogadores));
		
		return partida;
	}	

	/**
	 * @param participantes
	 * @param jogadas
	 * @return o vencedor da partida
	 */
	private String vencedorPartida(List<Jogador> jogadores) {
		List<Integer> numeroJogadas = new ArrayList<>(); 		
		String retornoVencedor = "";
		String jogadorVencedor = "";


		for(int i=0; i<=2; i++) {			
			numeroJogadas.add(escolhaPorJogador(jogadores.get(i).getJogada().getEscolha()));
		}

		retornoVencedor =  vitoria(numeroJogadas.get(0), numeroJogadas.get(1), numeroJogadas.get(2));

		
		if(retornoVencedor.contains("Jogador 1 -")) {
			jogadorVencedor = "Jogador 1";
		}

		if(retornoVencedor.contains("Jogador 2 -")){
			jogadorVencedor = "Jogador 2";
		}

		if(retornoVencedor.contains("Jogador 3 -")){
			jogadorVencedor = "Jogador 3";
		}
		
		if(retornoVencedor.equals("")) {
			jogadorVencedor = "Mais de um jogador com a mesma jogada, ou uma jogada elimina a outra. Jogue novamente!";
		}else {
			jogadorVencedor = retornoVencedor;
		}

		return jogadorVencedor;
	}

	/**
	 * retorna o numero refente a escola do jogador
	 * @param escolha
	 * @return
	 */
	private int escolhaPorJogador(String escolha){
		int numero = 0;

		switch(escolha.toString()){
		case "SPOCK":
			numero = 1;
			break;
		case "TESOURA":
			numero = 2;
			break;
		case "PAPEL":
			numero = 3;
			break;
		case "PEDRA":
			numero = 4;
			break;
		case "LAGARTO":
			numero = 5;
			break; 
		default:
			numero = 0;
			break;
		}

		return numero;
	}

	/**
	 * @param j1
	 * @param j2
	 * @param j3
	 * Recebe 3 escolhas dos jogadores e retorna um vencedor
	 */
	private String vitoria(Integer j1, Integer j2, Integer j3) {
		String vencedor = "";
		if(empatou(j1, j2, j3)) {
			vencedor = "Embatou, jogue novamente!";
		}else{
			vencedor = jogadorVencedor(j1, j2, j3);
		}
		
		return vencedor	; 
	}


	/**
	 * Recebe os jogadores e retorna a jogada vencedora
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String jogadorVencedor(Integer j1, Integer j2, Integer j3) {
		String vencedor = "";
	
		if(vitoriaSpock(j1, j2, j3) != ""){
			vencedor = vitoriaSpock(j1, j2, j3);
		}

		if(vitoriaTesoura(j1, j2, j3) != ""){
			vencedor = vitoriaTesoura(j1, j2, j3);
		}

		if(vitoriaPapel(j1, j2, j3) != ""){
			vencedor = vitoriaPapel(j1, j2, j3);
		}
		if(vitoriaPedra(j1, j2, j3) != ""){
			vencedor = vitoriaPedra(j1, j2, j3);
		}
		if(vitoriaLagarto(j1, j2, j3) != ""){
			vencedor = vitoriaLagarto(j1, j2, j3);
		}

		return vencedor;
	}


	/**
	 * Recebe os parametros jogadas referente a cada jogador e verifica se empatou
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private Boolean empatou(Integer j1, Integer j2, Integer j3) {
		Boolean vencedor = false;
		if(j1==j2 && j2==j3) {
			vencedor = true;
		}
		return vencedor;
	}

	/**
	 * Recebe os parametros jogadas referente a cada jogador e retorna a jogada SPOCK vencededora 
	 * SPOCK - 1
	 * O Spock quebra tesoura
	 * O Spock vaporiza a pedra
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String vitoriaSpock(Integer j1, Integer j2, Integer j3) {
		String jVencedor = "";

		if(j1 == 1 && ((j2 == 2 || j2 == 4)&&(j3 == 2 || j3 == 4))){
			jVencedor = "Jogador 1 - ";
		}

		if(j2 == 1 && ((j1 == 2 || j1 == 4)&&(j3 == 2 || j3 == 4))){
			jVencedor = "Jogador 2 - ";
		}

		if(j3 == 1 && ((j1 == 2 || j1 == 4)&&(j2 == 2 || j2 == 4))){
			jVencedor = "Jogador 3 - ";
		}

		return jVencedor;
	}

	/**
	 * Recebe os parametros jogadas referente a cada jogador e retorna a jogada TESOURA vencededora 
	 * TESOURA - 2
	 * A tesoura mata o lagarto
	 * A tesoura corta papel
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String vitoriaTesoura(Integer j1, Integer j2, Integer j3) {
		String jVencedor = "";

		if(j1 == 2 && ((j2 == 5 || j2 == 3)&&(j3 == 5 || j3 == 3))){
			jVencedor = "Jogador 1 - ";
		}

		if(j2 == 2 && ((j1 == 5 || j1 == 3)&&(j3 == 5 || j3 == 3))){
			jVencedor = "Jogador 2 - ";
		}

		if(j3 == 2 && ((j1 == 5 || j1 == 3)&&(j2 == 5 || j2 == 3))){
			jVencedor = "Jogador 3 - ";
		}

		return jVencedor;
	}

	/**
	 * Recebe os parametros jogadas referente a cada jogador e retorna a jogada PAPEL vencededora 
	 * PAPEL - 3
	 * O papel refuta o Spock
	 * O papel cobre a pedra
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String vitoriaPapel(Integer j1, Integer j2, Integer j3) {
		String jVencedor = "";

		if(j1 == 3 && ((j2 == 1 || j2 == 4)&&(j3 == 1 || j3 == 4))){
			jVencedor = "Jogador 1 - ";
		}

		if(j2 == 3 && ((j1 == 1 || j1 == 4)&&(j3 == 1 || j3 == 4))){
			jVencedor = "Jogador 2 - ";
		}

		if(j3 == 3 && ((j1 == 1 || j1 == 4)&&(j2 == 1 || j2 == 4))){
			jVencedor = "Jogador 3 - ";
		}
		
		return jVencedor;
	}

	/**
	 * Recebe os parametros jogadas referente a cada jogador e retorna a jogada PEDRA vencededora 
	 * PEDRA - 4
	 * A pedra esmaga o lagarto
	 * A pedra quebra a tesoura
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String vitoriaPedra(Integer j1, Integer j2, Integer j3) {
		String jVencedor = "";

		if(j1 == 4 && ((j2 == 5 || j2 == 2)&&(j3 == 5 || j3 == 2))){
			jVencedor = "Jogador 1 - ";
		}

		if(j2 == 4 && ((j1 == 5 || j1 == 2)&&(j3 == 5 || j3 == 2))){
			jVencedor = "Jogador 2 - ";
		}

		if(j3 == 4 && ((j1 == 5 || j1 == 2)&&(j2 == 5 || j2 == 2))){
			jVencedor = "Jogador 3 - ";
		}


		return jVencedor;
	}

	/**
	 * Recebe os parametros jogadas referente a cada jogador e retorna a jogada LAGARTO vencededora 
	 * LAGARTO - 5
	 * O lagarto envenena o Spock
	 * O lagarto come o papel
	 * @param j1
	 * @param j2
	 * @param j3
	 * @return
	 */
	private String vitoriaLagarto(Integer j1, Integer j2, Integer j3) {
		String jVencedor = "";

		if(j1 == 5 && ((j2 == 1 || j2 == 3)&&(j3 == 1 || j3 == 3))){
			jVencedor = "Jogador 1 - ";
		}

		if(j2 == 5 && ((j1 == 1 || j1 == 3)&&(j3 == 1 || j3 == 3))){
			jVencedor = "Jogador 2 - ";
		}

		if(j3 == 5 && ((j1 == 1 || j1 == 3)&&(j2 == 1 || j2 == 3))){
			jVencedor = "Jogador 3 - ";
		}

		return jVencedor;
	}


	/**
	 * @param jogador
	 * @return um jogador com uma jogada
	 */
	private Jogada realizaJogada(Jogador jogador, Long contador){			

		Jogada jogada = new Jogada();		
		jogada.setId(contador);
		jogada.setEscolha(UtilsJogadas.random().toString());

		return jogada;
	}
}

