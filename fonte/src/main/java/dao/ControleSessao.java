package dao;

import java.sql.*;
import java.util.Random;

import models.Usuario;

public class ControleSessao extends DAO {
	public ControleSessao() {
		super();
	}
	
	/**
	 * Cria uma id para a sessao do usuario.
	 * */
	private int gerarID() {
		Random rand = new Random();
		int id = -1;
		
		boolean valid = false;
		while(!valid) {
			id = rand.nextInt(9000) + 1000;
			try {
				Statement stat = connection.createStatement();
				String sql = "SELECT * FROM \"public\".\"Sessao\" WHERE id = " + id + ";";
				ResultSet rs = stat.executeQuery(sql);
				if(!rs.next()) {
					valid = true;
				}
				stat.close();
			}catch(SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return id;
	}
	
	/**
	 * Inicia uma sessao no banco de dados.
	 * @return o codigo da sessao
	 * */
	public int iniciarSessao(Usuario user) {
		int code = buscarSessao(user);
		if(code == -1) {			
			code = gerarID();
			try {
				Statement stat = connection.createStatement();
				String sql = "INSERT INTO \"public\".\"Sessao\" (id, usuario) VALUES (" + code + ", '" + user.getCpf() + "');";
				stat.executeUpdate(sql);
				stat.close();
			}catch(SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return code;
	}
	
	/**
	 * Procura sessoes antigas de um usuario
	 * @return id da antiga sessao
	 * */
	private int buscarSessao(Usuario user) {
		int result = -1;
		
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM \"public\".\"Sessao\" WHERE usuario = '" + user.getCpf() + "';";
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("id");
			}
		}catch(SQLException err) {
			System.out.println(err.getMessage());
		}
		
		return result;
	}
	
	
	public boolean validarSessao(int id) {
		boolean status = false;
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM \"public\".\"Sessao\" WHERE id = " + id + ";";
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				status = true;
			}
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
		}
		return status;
	}
}
