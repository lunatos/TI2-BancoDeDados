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
			Scanner scan = new Scanner(new File("src/main/resources/evento.html"));
			while(scan.hasNextLine()) {
				page += scan.nextLine() + "\n";
			}
		}catch(FileNotFoundException err){
			System.out.println(err.getMessage());
		}
		
		return page;
	}
}
