package dao;

import java.sql.*;
import java.util.Random;

public class ControleEvento extends DAO{
	public ControleEvento() {
		super();
	}
	
	/**
	 * Cria uma id para a relacao.
	 * */
	public int gerarID() {
		Random rand = new Random();
		int id = -1;
		
		boolean valid = false;
		while(!valid) {
			id = rand.nextInt(9000) + 1000;
			try {
				Statement stat = connection.createStatement();
				String sql = "SELECT * FROM \"public\".\"ControladorEvento\" WHERE id = " + id + ";";
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
	
	public boolean adicionarRelacao(int chaveEvento, String cpf) {
		boolean status = true;
		int code = gerarID();
		try {
			Statement stat = connection.createStatement();
			String sql = 	"INSERT INTO \"public\".\"ControladorEvento\" (id, evento, participante)" +
							"VALUES (" + code + ", " + chaveEvento + ", '" + cpf + "');";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
			status = false;
		}
		
		return status;
	}
	
	public boolean removerRelacao(int chaveEvento, String cpf) {
		boolean status = true;
		try {
			Statement stat = connection.createStatement();
			String sql = "DELETE FROM \"public\".\"ControladorEvento\" WHERE evento = " + chaveEvento + "AND participante = '" + cpf + "';";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
			status = false;
		}
		
		return status;
	}
	
	public boolean checarParticipacao(int chaveEvento, String cpf) {
		boolean status = false;
		try {
			Statement stat = connection.createStatement();
			String sql = "SELECT * FROM \"public\".\"ControladorEvento\" WHERE evento = " + chaveEvento + ";";
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				status = true;
			}
		}catch(SQLException err) {
			System.out.println(err.getMessage());
		}
		return status;
	}
}
