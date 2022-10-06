package models;

public class Evento {
	private int id;
	private String dono;
	private String nome;
	private int qtdParticipantes, maxParticipantes;
	private String data;
	private String horario;
	private String endereco;
	private String descricao;
	private boolean publico;
	
	public Evento(int id, String dono, String nome, int maxParticipantes, String data, String horario, String endereco, String descricao, boolean publico) {
		this.id = id;
		this.dono = dono;
		this.nome = nome;
		qtdParticipantes = 0;
		this.maxParticipantes = maxParticipantes;
		this.data = data;
		this.horario = horario;
		this.endereco = endereco;
		this.descricao = descricao;
		this.publico = publico;
	}
	
	public Evento(int id, String dono, String nome, int maxParticipantes, int qtdParticipantes, String data, String horario, String endereco, String descricao, boolean publico) {
		this.id = id;
		this.dono = dono;
		this.nome = nome;
		this.qtdParticipantes = qtdParticipantes;
		this.maxParticipantes = maxParticipantes;
		this.data = data;
		this.horario = horario;
		this.endereco = endereco;
		this.descricao = descricao;
		this.publico = publico;
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
	
	public String getData() {
		return data;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public boolean getPrivacidade() {
		return publico;
	}
}