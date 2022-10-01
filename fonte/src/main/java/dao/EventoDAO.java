package dao;

import java.sql.*;

import models.Evento;

public class EventoDAO extends DAO {
	public EventoDAO() {
		super();
	}
	
	public boolean createEvento(Evento evento) {
		boolean status = true;
		try {
			Statement stat = connection.createStatement();
			String sql = 	"INSERT INTO \"public\".\"Evento\" (id, dono, nome, qtdParticipantes, maxParticipantes, data, endereco, descricao)" +
							"VALUES (" + evento.getId() + ", '" + evento.getDono() + "', '" + evento.getNome() + "', " +
							evento.getQtdParticipantes() + ", " + evento.getMaxParticipantes() + ", '" + evento.getData().toString() + "', '" +
							evento.getEndereco() + "', '" + evento.getDescricao() + "');";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
			status = false;
		}
		return status;
	}
	
	public boolean updateEvento(Evento newEvento) {
		boolean status = true;
		try {
			Statement stat = connection.createStatement();
			String sql = 	"UPDATE \"public\".\"Evento\" SET id = " + newEvento.getId() + ", dono = '" +
							newEvento.getDono() + "', nome = '" + newEvento.getNome() + "', qtdParticipantes = " +
							newEvento.getQtdParticipantes() + ", maxParticipantes = " + newEvento.getMaxParticipantes() +
							", data = '" + newEvento.getData().toString() + "', endereco = '" + newEvento.getEndereco() +
							"', descricao = '" + newEvento.getDescricao() +"' WHERE id = " + newEvento.getId() + ";";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
			status = false;
		}
		return status;
	}
	
	public boolean deleteEvento(int id) {
		boolean status = true;
		try {
			Statement stat = connection.createStatement();
			String sql = "DELETE FROM \"public\".\"Evento\" WHERE id = " + id + ";";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			System.out.println(err.getMessage());
			status = false;
		}
		return status;
	}
}
