import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

public class Cliente {

    private final Servidor servidor;

    public Cliente(Servidor servidor) {
        System.out.println("Iniciando servidor...");
        this.servidor = servidor;
    }

    public static void main(String[] argv) {
        try {
            Servidor servidor = (Servidor) Naming.lookup("rmi://127.0.0.1/ServidorDB");
            Cliente c = new Cliente(servidor);

            try (Scanner entrada = new Scanner(System.in)) {
                Short op = 0;
                String aux = "";

                c.menu();
                do {
                    op = entrada.nextShort();

                    switch (op) {
                        case 1:
                            servidor.pegarTodos().forEach(System.out::println);
                            break;
                        case 2:
                            Livro livro = new Livro();
                            System.out.print("ISBN: ");
                            aux = entrada.nextLine();
                            livro.setIsbn(aux);
                            System.out.print("Nome: ");
                            aux = entrada.nextLine();
                            livro.setNome(aux);
                            System.out.print("Autor: ");
                            aux = entrada.nextLine();
                            livro.setAutor(aux);
                            System.out.print("Quantidade: ");
                            Integer qtd = entrada.nextInt();
                            livro.setQuantidade(qtd);
                            
                            servidor.addLivro(livro);
                            break;
                        case 3:
                            System.out.println("Alterar quantidade");
                            break;
                        case 4:
                            aux = entrada.nextLine();
                            servidor.delete(aux);
                            break;
                        case 9:
                            c.menu();
                            break;
                        default:
                            c.menu();
                            break;
                    }

                } while (op != 0);
            }

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println("Falhou na inicialização do Cliente.\n" + e);
            System.err.println("Certifique-se que tanto o Servidor de Registos como a Aplicação Servidora estão a correr correctamente.\n");
        }

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
