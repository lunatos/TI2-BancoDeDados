package dao;

import java.sql.*;
import models.Usuario;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
	}
	
	public boolean addUsuario(Usuario newUser) {
		boolean status = true;
		
		try {
			Statement stat = connection.createStatement();
			String sql = 	"INSERT INTO \"public\".\"Usuario\" (cpf, telefone, nome, sobrenome, login, senha) " +
					 		"VALUES ('" + newUser.getCpf() + "', '" + newUser.getTelefone() + "', '" +
					 		newUser.getNome() + "', '" + newUser.getSobrenome() + "', '" +
							newUser.getLogin() + "', '" + newUser.getSenha() + "');";
			
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			status = false;
			System.out.println(err.getMessage());
		}
		
		return status;
	}
	
	public boolean deleteUsuario(String cpf) {
		boolean status = true;
		
		try {
			Statement stat = connection.createStatement();
			String sql = "DELETE FROM \"public\".\"Usuario\" WHERE cpf = " + "'" + cpf + "'";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			status = false;
		}
		
		return status;
	}
	
	public Usuario getUsuario(String cpf) {
		Usuario user = null;
		
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM \"public\".\"Usuario\" WHERE cpf = '" + cpf + "'";
			ResultSet r = stat.executeQuery(sql);
			user = new Usuario(
					r.getString("cpf"), 
					r.getString("telefone"), 
					r.getString("nome"),
					r.getString("sobrenome"),
					r.getString("login"),
					r.getString("senha")
					);
		}catch(SQLException err) {
			System.out.println(err.getMessage());
		}
		
		return user;
	}
	
	/**
	 * Autentica��o de usu�rios para o sistema de login. Recebe o nome de login e a senha.
	 * @param login = nome de login
	 * @param senha = senha do usu�rio
	 * @return retorna true se o login e o usu�rio estiverem corretos
	 * */
	public boolean autenticar(String login, String senha) {
		boolean status = true;
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM \"public\".\"Usuario\" WHERE login = '" + login + "' AND senha = '" + senha + "';";
			ResultSet rs = stat.executeQuery(sql);
			status = rs.next();
			stat.close();
		}catch(SQLException err) {
			System.out.println(err);
		}
		return status;
	}
}
