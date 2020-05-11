package com.jogo.jogadoresjokenpo.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogo.jogadoresjokenpo.domain.Jogador;
import com.jogo.jogadoresjokenpo.repository.JogadorRepository;
import com.jogo.jogadoresjokenpo.service.exception.JogadorNaoEncontrado;
import com.jogo.jogadoresjokenpo.services.ServiceJogador;

@Service
public class JogadorServiceImpl implements ServiceJogador{

	@Autowired
	private JogadorRepository jogadorRepository;

	@Override
	public List<Jogador> listar() {		
		return jogadorRepository.findAll();
	}

	@Override
	public Jogador salvar(Jogador jogador) {
		jogador.setId(null);
		return jogadorRepository.save(jogador);
	}

	@Override
	public Jogador listarPorId(Long jogadorId) {
		Optional<Jogador> jgd = jogadorRepository.findById(jogadorId);
		try {
			if (null == jgd.get()) {
				throw new JogadorNaoEncontrado("O jogador não existe.");
			}				
		} catch (Exception e) {
			throw new JogadorNaoEncontrado("O jogador não existe.");
		}
		Jogador jogador = jgd.get();

		return jogador;
	}

	@Override
	public void atualiza(Jogador jogador) {
		verificaJogador(jogador.getId());
		jogadorRepository.save(jogador);

	}

	private void verificaJogador(Long jogadorId) {
		listarPorId(jogadorId);
	}

	@Override
	public void excluir(Long jogadorId) {
		verificaJogador(jogadorId);
		jogadorRepository.deleteById(jogadorId);		

	}

}
