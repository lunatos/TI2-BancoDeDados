package services;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import models.Evento;
import dao.EventoDAO;

public class PaginaEvento {
	
	public static String createPaginaEvento(int id) {
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
		String desc = "<p>" + e.getDescricao() + "</p>\n";
		String data = "<h4>Data:" + e.getData() + "</h4>\n";
		String hora = "<h4>Horário:" + e.getHorario() + "</h4>\n";
		String local = "<h4>Endereço:" + e.getEndereco() + "</h4>\n";
		
		page = page.replaceFirst("<NOME-EVENTO>",titulo);
		page = page.replaceFirst("<TIPO>",tipo);
		page = page.replaceFirst("<DESC>",desc);
		page = page.replaceFirst("<DATA>", data);
		page = page.replaceFirst("<HORA>",hora);
		page = page.replaceFirst("<LOCAL>",local);
		
		//checa se o evento e publico
		if(e.getPrivacidade()) {
			page = page.replaceFirst("<BOTAO-PARTICIPAR>", "<a href=\"/entrarEvento/" + e.getId() + "\">Participar</a>");
		}
		
		return page;
	}
}
