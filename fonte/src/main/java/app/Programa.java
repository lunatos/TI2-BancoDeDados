package app;

import static spark.Spark.*;
import dao.UsuarioDAO;
import dao.ControleSessao;
import models.Usuario;

public class Programa {
	
	//determina a id de sess�o deste computador
	private static int sessionKey = -1;
	
	public static void main(String[] args) {
		staticFiles.location("/public");
		port(4567);
		get("/", (req, res) -> {
			ControleSessao cont = new ControleSessao();
			if(cont.validarSessao(sessionKey)) {
				return null;
			}else {				
				res.redirect("index.html");
				return null;
			}			
		});
		
		//pagina de login do site
		get("/login", (req, res) -> {
			res.redirect("login.html");
			return null;
		});
		
		//requisi��o para autenticar o usu�rio
		get("/autenticar", (req, res) -> {
			UsuarioDAO u = new UsuarioDAO();
			boolean status = u.autenticar(req.queryParams("login"), req.queryParams("password"));
			u.disconnect();
			if(status) {
				ControleSessao cont = new ControleSessao();
				sessionKey = cont.iniciarSessao();
				cont.disconnect();
				return "autenticado com sucesso!";
			}else {
				return "falha na autenticacao";
			}	
		});
		
		//p�gina de cadastro de usu�rio
		get("/cadastro", (req, res) -> {
			res.redirect("cadastro.html");
			return null;
		});
		
		//requisi��o para enviar o novo usu�rio para o banco de dados
		get("/cadastro/send", (req, res) -> {
			Usuario newUser = new Usuario
					(req.queryParams("cpf"), 
					req.queryParams("telefone"),
					req.queryParams("pnome"), 
					req.queryParams("unome"),
					req.queryParams("login"),
					req.queryParams("senha"));		
			
			UsuarioDAO access = new UsuarioDAO();
			boolean status = access.addUsuario(newUser);
			if(status) {
				return "cadastro realizado com sucesso";
			}else {
				return "falha no cadastro";
			}
		});
	}
}
