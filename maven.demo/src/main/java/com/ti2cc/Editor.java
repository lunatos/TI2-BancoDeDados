package com.ti2cc;

public class Editor {
	private int codigo;
	private String login;
	private String senha;
	private String email;
	
	public Editor() {
		this.codigo = -1;
		this.login = "";
		this.senha = "";
		this.email = "";
	}
	
	public Editor(int codigo, String login, String senha, String email) {
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", login=" + login + ", senha=" + senha + ", email=" + email + "]";
	}	
}
