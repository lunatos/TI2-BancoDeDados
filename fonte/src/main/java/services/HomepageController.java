package services;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HomepageController {

	/**
	 * Cria a pagina principal para usuarios nao logados
	 * */
	public static String createPageUnlogged() {
		String page = "";
		
		try {			
			Scanner scan = new Scanner(new File("src/main/resources/public/home.html"));
			while(scan.hasNext()) {
				page += scan.nextLine() + "\n";
			}
			page = page.replaceAll("<LOGIN-BUTTON>", "<li class=\"nav-item\"><a href=\"/login\" class=\"btn btn-outline-light bg-secondary ml-4\"> Login </a></li>");
		}catch(FileNotFoundException err){
			System.out.println(err.getMessage());
		}		
		
		return page;
	}
	
	/**
	 * Cria a pagina principal para usuarios logados
	 * */
	public static String createPageLogged() {
		String page = "";
		
		try {			
			Scanner scan = new Scanner(new File("src/main/resources/public/home.html"));
			while(scan.hasNext()) {
				page += scan.nextLine() + "\n";
			}
			page = page.replaceFirst("<LOGIN-BUTTON>", "<li class=\"nav-item\"><a href=\"\" id=\"user-view\"><i class=\"fa-solid fa-circle-user\"></i></a></li>");
		}catch(FileNotFoundException err){
			System.out.println(err.getMessage());
		}
		
		return page;
	}
	
}
