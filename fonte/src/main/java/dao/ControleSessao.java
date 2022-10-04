package dao;

import java.sql.*;
import java.util.Random;

public class ControleSessao extends DAO {
	public ControleSessao() {
		super();
	}
	
	/**
	 * Cria uma id para a sessão do usuário.
	 * */
	private int gerarID() {
		Random rand = new Random();
		int id = -1;
		
		boolean valid = false;
		while(!valid) {
			id = rand.nextInt();
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
	 * Inicia uma sessão no banco de dados.
	 * @return o código da sessão
	 * */
	public int iniciarSessao() {
		int code = -1;
		code = gerarID();
		try {
			Statement stat = connection.createStatement();
			String sql = "INSERT INTO \"public\".\"Sessao\" (id) VALUES (" + code + ");";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
		}
		return code;
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
