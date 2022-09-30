package models;

import java.util.Date;

public class PoolLocal {
	private int id;
	private int qtdVotos;
	private int qtdOpcoes;
	private Date tempoLimite;
	private String info;
	private String[] opcoes;
	private int[] votoOpcao;
	
	public PoolLocal(int id, Date tempoLimite, String info, String[] opcoes) {
		this.id = id;
		this.qtdVotos = 0;
		this.qtdOpcoes = opcoes.length;
		this.tempoLimite = tempoLimite;
		this.info = info;
		this.opcoes = opcoes;
		this.votoOpcao = new int[opcoes.length];
	}
	
	/**
	 * {@summary Adiciona um voto em uma das opções da pool.}
	 * @param opcao = opcao a,b,c ... da pool.
	 * */
	public void addVoto(int opcao) {
		if(opcao >= votoOpcao.length) throw new ArrayIndexOutOfBoundsException();
		qtdVotos++;
		votoOpcao[opcao] += 1;
	}
	
	/**
	 * Retorna o índice da opção com mais votos na pool.
	 * */
	public int getResultado() {
		int maior = -1;
		for(int i = 0; i < qtdOpcoes; i++) {
			if(votoOpcao[i] > maior) {
				maior = i;
			}
		}
		return maior;
	}
	
	public String getOpcao(int i) {
		if(i >= votoOpcao.length) throw new ArrayIndexOutOfBoundsException();
		return opcoes[i];
	}
	
	public Date getData() {
		return tempoLimite;
	}
	
}
