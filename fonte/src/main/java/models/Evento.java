package models;

import java.util.Date;

public class Evento {
	private int id;
	private String dono;
	private String nome;
	private int qtdParticipantes, maxParticipantes;
	private Date data;
	private String endereco;
	private String descricao;
	
	public Evento(int id, String dono, String nome, int maxParticipantes, Date data, String endereco, String descricao) {
		this.id = id;
		this.dono = dono;
		this.nome = nome;
		qtdParticipantes = 0;
		this.maxParticipantes = maxParticipantes;
		this.data = data;
		this.endereco = endereco;
		this.descricao = descricao;
	}
	
	public void addParticipante() throws Exception{
		if(qtdParticipantes >= maxParticipantes) throw new Exception("Limite de participantes atingido!");
		qtdParticipantes++;
	}
	
	//getters
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDono() {
		return dono;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public int getMaxParticipantes() {
		return maxParticipantes;
	}

	public int getQtdParticipantes() {
		return qtdParticipantes;
	}
	
	public Date getData() {
		return data;
	}
}