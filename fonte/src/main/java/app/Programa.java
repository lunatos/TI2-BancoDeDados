package app;

import static spark.Spark.*;
import dao.UsuarioDAO;
import models.Usuario;

public class Programa {

	public static void main(String[] args) {
		staticFiles.location("/public");
		port(4567);
		get("/", (req, res) -> {
			res.redirect("index.html");
			return null;
		});
		
		get("/login", (req, res) -> {
			res.redirect("login.html");
			return null;
		});
		
		get("/autenticar", (req, res) -> {
			UsuarioDAO u = new UsuarioDAO();
			boolean status = u.autenticar(req.queryParams("login"), req.queryParams("password"));
			u.disconnect();
			if(status) {
				return "autenticado com sucesso!";
			}else {
				return "falha na autenticação.";
			}	
		});
		
		get("/cadastro", (req, res) -> {
			res.redirect("cadastro.html");
			return null;
		});
		
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
