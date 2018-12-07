import java.rmi.*;

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
	}


}