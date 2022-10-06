package app;

import static spark.Spark.*;

import dao.*;
import models.*;
import services.*;

public class Programa {
	
	public static void main(String[] args) {
		staticFiles.location("/public");
		port(4567);
		
		//PÁGINA PRINCIPAL
		//--------------------------------------------------------------------------------+
		
		//raiz, página principal do site
		get("/", (req, res) -> {
			//testa se o usuário está logado
			ControleSessao cont = new ControleSessao();
			boolean status = false;
			if(req.cookie("key") != null) {
				status = cont.validarSessao(Integer.parseInt(req.cookie("key")));
			}
			cont.disconnect();
			//
			
			if(status) {
				return HomepageController.createPageLogged();
			}else {				
				return HomepageController.createPageUnlogged();
			}
		});
		
		
		//LOGIN E CONTROLE DE SESSÃO
		//--------------------------------------------------------------------------------+
		
		//pagina de login do site
		get("/login", (req, res) -> {
			res.redirect("login.html");
			return null;
		});
		
		
		//requisicao para autenticar o usuario
		get("/autenticar", (req, res) -> {
			UsuarioDAO u = new UsuarioDAO();
			boolean status = u.autenticar(req.queryParams("login"), req.queryParams("password"));
			
			if(status) {
				ControleSessao cont = new ControleSessao();
				int key = cont.iniciarSessao(u.getUsuario(req.queryParams("login"), req.queryParams("password")));
				
				if(req.cookie("key") == null) {					
					res.cookie("/", "key", String.valueOf(key), 3600, false);
				}
				
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
		
		//chamada para deslogar um usuario
		get("/logout", (req, res) -> {
			res.removeCookie("key");
			res.redirect("/");
			return null;
		});
		
		
		//RECUPERAR A SENHA
		//--------------------------------------------------------------------------------+
		
		//pagina para recuperar a senha do usuario
		get("/recuperar", (req, res) -> {
			res.redirect("recUsuario.html");
			return null;
		});
		
		//requisicao para enviar a nova senha
		get("/recuperar/send", (req, res) -> {
			String cpf = req.queryParams("cpf");
			String novaSenha = req.queryParams("novaSenha");
			
			UsuarioDAO u = new UsuarioDAO();
			Usuario novoUsu = u.getUsuario(cpf);
			novoUsu.setSenha(novaSenha);
			u.updateUsuario(novoUsu);
			
			res.redirect("/login");
			return null;
		});
		
		
		//CADASTRO DE USUÁRIOS
		//--------------------------------------------------------------------------------+
		
		//pagina de cadastro de usuario
		get("/cadastro", (req, res) -> {
			res.redirect("cadastro.html");
			return null;
		});
		
		//requisicao para enviar o novo usuario para o banco de dados
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
		
		
		//CRIACAO DE EVENTOS E MANIPULACAO
		//--------------------------------------------------------------------------------+
		
		get("/criarEvento", (req, res) -> {
			//testa se o usuário está logado
			ControleSessao cont = new ControleSessao();
			boolean status = false;
			if(req.cookie("key") != null) {
				status = cont.validarSessao(Integer.parseInt(req.cookie("key")));
			}
			cont.disconnect();
			//
			
			if(status) {				
				res.redirect("criarEvento.html");
			}else {
				res.redirect("/login");
			}
			return null;
		});
		
		get("/criarEvento/send", (req, res) -> {
			//objetos de acesso
			ControleSessao contr = new ControleSessao();
			EventoDAO eDao = new EventoDAO();
			Usuario user = contr.recuperarUsuario(Integer.parseInt(req.cookie("key")));
			Evento evento = new Evento(
					eDao.gerarID(), 
					user.getCpf(),
					req.queryParams("nome"),
					Integer.parseInt(req.queryParams("maxP")),
					req.queryParams("data"),
					req.queryParams("horario"),
					req.queryParams("endereco"),
					req.queryParams("descricao"),
					Boolean.valueOf(req.queryParams("publico"))
					);
			//
			//inserindo o evento no banco de dados
			eDao.createEvento(evento);
			evento = null;
			user = null;
			
			eDao.disconnect();
			contr.disconnect();
			return "criou evento";
		});
		
		get("/evento/:id", (req, res) -> {
			int id = Integer.parseInt(req.params("id"));
			return PaginaEvento.createPaginaEvento(id);
		});
	}
}
