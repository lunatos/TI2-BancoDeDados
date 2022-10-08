package services;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import dao.*;
import models.*;

public class AutenticarService {
	
	public static void autenticarUsuario(Request req, Response res) {
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
		}else {
			u.disconnect();
			res.redirect("/login");
		}
	}
}
