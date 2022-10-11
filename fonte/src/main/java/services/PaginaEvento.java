package services;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;

import models.Evento;
import models.Usuario;
import dao.EventoDAO;
import dao.ControleEvento;

public class PaginaEvento {
	
	public static String createPaginaEvento(int id) {
		ControleEvento contE = new ControleEvento();
		EventoDAO eDao = new EventoDAO();
		Evento e = eDao.getEvento(id);
		String page = "";
		
		try {
			Scanner scan = new Scanner(new File("src/main/resources/public/evento.html"));
			while(scan.hasNextLine()) {
				page += scan.nextLine() + "\n";
			}
		}catch(FileNotFoundException err){
			System.out.println(err.getMessage());
		}
		
		//insersao das informacoes do evento
		String titulo = "<h1>" + e.getNome() + "</h1>\n";
		String tipo = "<h2>Tipo: " + (e.getPrivacidade() ? "Público" : "Privado") + "</h2>\n";
		String linkChat = "<a href=\"/chat/" + e.getId() + "\">Chat</a>";
		String linkConfirmados = "<a href=\"/confirmados/" + e.getId() + "\">Lista de confirmados</a>";
		String desc = "<p>" + e.getDescricao() + "</p>\n";
		String data = "<h4>Data:" + e.getData() + "</h4>\n";
		String hora = "<h4>Horário:" + e.getHorario() + "</h4>\n";
		String local = "<h4>Endereço:" + e.getEndereco() + "</h4>\n";
		String count = "<h5>" + e.getQtdParticipantes() + "/" + e.getMaxParticipantes() + "</h5>\n";
		
		page = page.replaceFirst("<NOME-EVENTO>",titulo);
		page = page.replaceFirst("<TIPO>",tipo);
		page = page.replaceFirst("<LINK-CHAT>", linkChat);
		page = page.replaceFirst("<LINK-CONFIRMADOS>", linkConfirmados);
		page = page.replaceFirst("<DESC>",desc);
		page = page.replaceFirst("<DATA>", data);
		page = page.replaceFirst("<HORA>",hora);
		page = page.replaceFirst("<LOCAL>",local);
		page = page.replaceFirst("<COUNT>", count);
		
		//checa se o evento e publico
		if(e.getPrivacidade()) {
			page = page.replaceFirst("<BOTAO-PARTICIPAR>", "<a href=\"/entrarEvento/" + e.getId() + "\">Participar</a>");
		}
		
		//cria a lista de participantes
		String listaPartic = "";
		List<Usuario> users = contE.recuperarParticipantes(id);
		
		listaPartic = "<ul>\n";
		for(int i = 0; i < users.size(); i++) {
			listaPartic += "<li>" + users.get(i).getNome() + " " + users.get(i).getSobrenome() + "</li>\n";
		}
		listaPartic += "</ul>\n";
		page = page.replaceFirst("<LISTA-PARTICIPANTES>", listaPartic);
		
		return page;
	}
}
