package com.jogo.jogadoresjokenpo.utils;

public enum Escolha {
	
	SPOCK(1, "Spock"),
	TESOURA(2, "Tesoura"),
	PAPEL(3, "Papel"),
	PEDRA(4, "Pedra"),
	LAGARTO(5, "Lagarto");
	
	private String descricao;
	
	Escolha(int codigo, String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
