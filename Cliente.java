import java.rmi.*;
import java.util.Scanner;

public class Cliente {
	
	private Servidor servidor;

	public Cliente() {
		System.out.println("Iniciando o Cliente...");
		// Vamos tentar ir aceder ao Servidor de Registos para recolher a interface
		try {
			servidor = (Servidor) Naming.lookup("rmi://127.0.0.1/ServidorDB");
		} catch (Exception e) {
			System.out.println("Falhou na inicialização do Cliente.\n" + e);
			System.out.println("Certifique-se que tanto o Servidor de Registos como a Aplicação Servidora estão a correr correctamente.\n");
			System.exit(0);
		}

	}
	public static void main(String[] argv) {
		Cliente c = new Cliente();
		Scanner scannner = new Scanner(System.in);

		Short op = 0;
		
		c.menu();
		do {
			op = scannner.nextShort();
			System.err.println(op);
		} while (op != 0);

		scannner.close();
	}

	public void menu() {
		System.out.println(" CRUD RMI: Livraria ");
		System.out.println("====================");
		System.out.println("1 - listar livros.  ");
		System.out.println("2 - adicionar livro.");
		System.out.println("3 - atualizar livro.");
		System.out.println("4 - remover livro.");
		System.out.println("9 - Menu.");
		System.out.println("0 - sair.");
	}
}