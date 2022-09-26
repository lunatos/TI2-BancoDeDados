package app;

import static spark.Spark.*;

public class Programa {

	public static void main(String[] args) {
		staticFiles.location("/public");
		get("/", (req, res) -> {
			res.redirect("index.html");
			return null;
		});
		
		get("/login", (req, res) -> "pagina de login");
		get("/cadastro", (req, resp) -> "pagina de cadastro");
	}
}
