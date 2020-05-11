package com.jogo.jogadoresjokenpo.utils;

public class UtilsJogadas {
	public static Escolha random() {
		return Escolha.values()[Double.valueOf(
				Math.random() * Escolha.values().length).intValue()];
	}
}
