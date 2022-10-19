package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
	
	private String cpf;
	private String telefone;
	private String pNome, uNome;
	private String login, senha;
	
	public Usuario(String cpf, String telefone, String pNome, String uNome, String login, String senha) {
		this.cpf = cpf;
		this.telefone = telefone;
		this.pNome = pNome;
		this.uNome = uNome;
		this.login = login;
		this.senha = senha;
	}
	
	//
	public  static BigInteger criptografia(String senha) {
		MessageDigest md5;
		BigInteger resp = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(senha.getBytes(), 0, senha.length());
			resp = new BigInteger(1, md5.digest());
		} catch(NoSuchAlgorithmException ex) {
			Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return resp;
	}
	
	//getters
	public String getCpf() {
		return cpf;
	}
	
	public String getTelefone() {
		return telefone;
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
	
	//setters
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
