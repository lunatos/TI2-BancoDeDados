package app;

import static spark.Spark.*;
import dao.UsuarioDAO;
import dao.ControleSessao;
import models.Usuario;

public class Programa {
	
	public static void main(String[] args) {
		staticFiles.location("/public");
		port(4567);
		
		//raiz, página principal do site
		get("/", (req, res) -> {
			System.out.println("entrou");
			ControleSessao cont = new ControleSessao();
			boolean status = cont.validarSessao(Integer.parseInt(req.cookie("key")));
			cont.disconnect();
			
			if(status) {
				return "logado";
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
		
		
		//requisicao para autenticar o usuario
		get("/autenticar", (req, res) -> {
			UsuarioDAO u = new UsuarioDAO();
			Usuario user = u.getUsuario(req.queryParams("login"), req.queryParams("password"));
			boolean status = u.autenticar(req.queryParams("login"), req.queryParams("password"));
			
			if(status) {
				ControleSessao cont = new ControleSessao();
				int key = cont.iniciarSessao(u.getUsuario(req.queryParams("login"), req.queryParams("password")));
				
				res.cookie("/", "key", String.valueOf(key), 50000, false);
				
				cont.disconnect();
				u.disconnect();
				res.redirect("/");
				return null;
			}else {
				u.disconnect();
				res.redirect("/login");
				return null;
			}	
		});
		
		//pagina de cadastro de usuario
		get("/cadastro", (req, res) -> {
			res.redirect("cadastro.html");
			return null;
		});
		
		//requisisao para enviar o novo usuario para o banco de dados
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
				res.redirect("/login");
				return null;
			}else {
				res.redirect("/cadastro");
				return null;
			}
		});
	}
}
