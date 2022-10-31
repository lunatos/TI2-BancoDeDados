package services;

import models.*;
import dao.*;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import spark.Request;
import spark.Response;

public class PaginaAmigos {
	
	public static String criarPaginaAmigos(Request req, Response res) {
		ControleSessao contS = new ControleSessao();
		UsuarioDAO uDao = new UsuarioDAO();
		AmizadeDAO aDao = new AmizadeDAO();
		
		Usuario user = contS.recuperarUsuario(Integer.parseInt(req.cookie("key")));
		List<Amizade> amigos = aDao.recuperarAmizades(user.getCpf());
		
		String page = "";
		try {
			Scanner scan = new Scanner(new File("src/main/resources/public/amigos.html"));
			while(scan.hasNextLine()) {
				page += scan.nextLine() + "\n";
			}
		}catch(FileNotFoundException err) {
			System.out.println(err.getMessage());
		}
		
		String list = "";
		list += "<ul>\n";
		for(int i = 0; i < amigos.size(); i++) {
			Usuario amigo = null;
			if(user.getCpf().equals(amigos.get(i).getUsuario1())) {
				amigo = uDao.getUsuario(amigos.get(i).getUsuario2());
			}else {
				amigo = uDao.getUsuario(amigos.get(i).getUsuario1());
			}
			
			list += "<li>" + amigo.getNome() + " " + amigo.getSobrenome() + "</li>\n";
		}
		list += "</ul>\n";
		page = page.replace("<LISTA-AMIGOS>", list);
		return page;
	}
}
