package models;

public class Usuario {
	
	private String cpf;
	private String[] telefones;
	private String pNome, uNome;
	private String login, senha;
	private String token;
	
	public Usuario(String cpf, String[] telefones, String pNome, String uNome, String login, String senha, String token) {
		this.cpf = cpf;
		this.telefones = telefones;
		this.pNome = pNome;
		this.uNome = uNome;
		this.login = login;
		this.senha = senha;
		this.token = token;
	}
	
	//getters
	public String getCpf() {
		return cpf;
	}
	
	public String[] getTelefones() {
		return telefones;
	}
	
	public String getNome() {
		return pNome;
	}
	
	public String getSobrenome() {
		return uNome;
	}
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getToken() {
		return token;
	}
}
