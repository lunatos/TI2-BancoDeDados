package services;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;

import models.Mensagem;
import models.Usuario;
import dao.ChatDAO;
import dao.UsuarioDAO;

import spark.Request;
import spark.Response;

public class PaginaChat {
	
	public static String createChat(Request req, Response res) {
		String page = "";
		
		try {
			Scanner scan = new Scanner(new File("src/main/resources/public/chat.html"));
			while(scan.hasNextLine()) {
				page += scan.nextLine() + "\n";
			}			
		}catch(FileNotFoundException err) {
			System.out.println(err.getMessage());
		}
		
		//update chat
		String msgList = "";
		String url = "/chat/" + Integer.parseInt(req.params("id")) + "/send";
		ChatDAO cDao = new ChatDAO();
		UsuarioDAO uDao = new UsuarioDAO();
		List<Mensagem> msgs = cDao.recuperarMensagensEvento(Integer.parseInt(req.params("id")));
		
		msgList += "<ul>\n";
		for(int i = 0; i < msgs.size(); i++) {
			Usuario u = uDao.getUsuario(msgs.get(i).getPessoa());
			msgList += 	"<li>" + "<div>" + "<h6>" + u.getNome() + " " + u.getSobrenome() + "</h6>" +
						"<p>" + msgs.get(i).getConteudo() + "</p></div></li>\n";
		}
		msgList += "</ul>";
		
		page = page.replaceFirst("<URL>", url);
		page = page.replaceFirst("<MENSAGENS>", msgList);
		
		return page;
	}
}
