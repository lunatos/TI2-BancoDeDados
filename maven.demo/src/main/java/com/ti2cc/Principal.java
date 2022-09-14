package com.ti2cc;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		dao.conectar();
		Editor[] usuariosN = dao.getUsuarios();
		//loop CRUD
		int codigo;
		String login, senha, email;
		Scanner ler = new Scanner(System.in);
		int escolha = 0;
		while(escolha != 5) {
			System.out.println("==== CRUD ==== ");
			System.out.println("1) Listar");
			System.out.println("2) Inserir");
			System.out.println("3) Excluir");
			System.out.println("4) Atualizar");
			System.out.println("5) Sair");
			System.out.println("Digite o que deseja fazer: ");
			escolha = ler.nextInt();
			switch(escolha) {
				case 1:
					//Mostrar usuários
					Editor[] usuarios = dao.getUsuarios();
					System.out.println("==== Mostrar usuários ==== ");		
					for(int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
					break;
				case 2:
					System.out.println("==== Inserir novo editor ==== ");
					System.out.println("Digite o login: ");
					login = ler.next();
					System.out.println("Digite a senha: ");
					senha = ler.next();
					System.out.println("Digite o email: ");
					email = ler.next();
					//Inserir um elemento na tabela
					codigo = usuariosN[usuariosN.length-1].getCodigo();
					Editor usuario = new Editor(codigo + 1, login, senha,email);
					if(dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					}
					break;
				case 3:
					System.out.println("==== Excluir editor ==== ");
					System.out.println("Digite o codigo do usuario: ");
					codigo = ler.nextInt();
					//Excluir usuário
					dao.excluirUsuario(codigo);
					break;
				case 4:
					System.out.println("==== Atualizar editor ==== ");
					System.out.println("Digite o codigo do usuario: ");
					codigo = ler.nextInt();
					System.out.println("Digite o novo login: ");
					login = ler.next();
					System.out.println("Digite a nova senha: ");
					senha = ler.next();
					System.out.println("Digite o novo email: ");
					email = ler.next();
					Editor usuarioA = new Editor(codigo, login, senha,email);
					//Atualizar usuário
					if(dao.atualizarUsuario(usuarioA) == true) {
						System.out.println("Atualização com sucesso -> " + usuarioA.toString());
					}
					break;
				case 5:
					System.out.println("==== Saindo... ==== ");
					break;
				default:
					System.out.println("==== Digite uma opção existente ==== ");
			}
		}
		dao.close();
	}
}