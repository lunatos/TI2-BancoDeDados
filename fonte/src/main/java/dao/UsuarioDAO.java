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
			String sql = 	"INSERT INTO Usuario (cpf, telefones, nome, sobrenome, login, senha, token) " +
					 		"VALUES ('" + newUser.getCpf() + "', ARRAY[";
			
			//telefones
			String[] tels = newUser.getTelefones();
			for(int i = 0; i < tels.length; i++) {
				if(i == tels.length - 1) {
					sql += "'" + tels[i] + "'], ";
				}else {
					sql += "'" + tels[i] + "', ";
				}
			}
				
			sql += 	"'" + newUser.getNome() + "', '" + newUser.getSobrenome() + "', '" +
					newUser.getLogin() + "', '" + newUser.getSenha() + "', '" + 
					newUser.getToken() + "');";
			
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			status= false;
		}
		
		return status;
	}
	
	public boolean deleteUsuario(String cpf) {
		boolean status = true;
		
		try {
			Statement stat = connection.createStatement();
			String sql = "DELETE FROM Usuario WHERE cpf = " + "'" + cpf + "'";
			stat.executeUpdate(sql);
			stat.close();
		}catch(SQLException err) {
			status = false;
		}
		
		return status;
	}
}
