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
		get("/", (req, res) -> HomeService.buildHomepage(req, res));
		
		
		//LOGIN E CONTROLE DE SESSÃO
		//--------------------------------------------------------------------------------+
		
		//pagina de login do site
		get("/login", (req, res) -> {
			res.redirect("login.html");
			return null;
		});
		
		
		//requisicao para autenticar o usuario
		get("/autenticar", (req, res) -> {
			AutenticarService.autenticarUsuario(req, res);
			return null;
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
			RecuperarService.enviarInfo(req, res);
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
			CadastroService.enviarInfo(req, res);
			return null;
		});
		
		
		//CRIACAO DE EVENTOS E MANIPULACAO
		//--------------------------------------------------------------------------------+
		
		//pagina para criacao de eventos
		get("/criarEvento", (req, res) -> {
			//testa se o usuário está logado
			ControleSessao cont = new ControleSessao();
			boolean status = cont.validarSessao(req, res);
			cont.disconnect();
			
			if(status) {				
				res.redirect("criarEvento.html");
			}else {
				res.redirect("/login");
			}
			return null;
		});
		
		//requisicao que envia o evento para o banco de dados
		get("/criarEvento/send", (req, res) -> {
			EventoService.criarEvento(req, res);
			return null;
		});
		
		//pagina que exibe o evento
		get("/evento/:id", (req, res) -> {
			int id = Integer.parseInt(req.params("id"));
			return PaginaEvento.createPaginaEvento(id);
		});
		
		//requisicao para participar de um evento
		get("/entrarEvento/:id", (req, res) ->{
			//testa se o usuário está logado
			ControleSessao cont = new ControleSessao();
			boolean status = cont.validarSessao(req, res);
			cont.disconnect();

			if(status) {				
				try {
					EventoService.entrarEvento(req, res);
				}catch(Exception err) {
					System.out.println("Evento lotado!");
				}
			}else {
				res.redirect("/login");
			}
			return null;
		});
		
		//CHAT DOS EVENTOS
		//--------------------------------------------------------------------------------+
		
		get("/chat/:id", (req, res) -> {
			return PaginaChat.createChat(req, res);
		});
		
		get("/chat/:id/send", (req, res) -> {
			ChatService.enviarMensagem(req, res);
			res.redirect("/chat/" + req.params("id"));
			return null;
		});
	}
}
