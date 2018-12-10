import java.net.MalformedURLException;
import java.rmi.*;
import java.util.InputMismatchException;
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

                c.menu();
                do {
                    System.out.print("\nEntre com a opção: ");
                    op = entrada.nextShort();

                    switch (op) {
                        case 1:
                            System.out.println("\nLivros na estante");
                            System.out.println("=================");
                            servidor.pegarTodos().forEach(System.out::println);
                            break;
                        case 2:
                            System.out.println("\nENTRE COM AS INFORMAÇÕES");
                            
                            System.out.print("ISBN: ");
                            String isbn = entrada.nextLine();
                            System.out.print("Nome: ");
                            String nome = entrada.nextLine();
                            System.out.print("Autor: ");
                            String autor = entrada.nextLine();
                            System.out.print("Quantidade: ");
                            Integer qtd = entrada.nextInt();

                            Livro livro = new Livro(isbn, nome, autor, qtd);
                            
                            servidor.addLivro(livro);
                            break;
                        case 3:
                            System.out.println("\nAlterar quantidade");
                            break;
                        case 4:
                            System.out.print("Digite o ISBN: ");
                            String isb = entrada.nextLine();			
                            System.out.println(servidor.delete(isb) ? "Livro excluído!" : "Livro não encontrado!");                        
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
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida");
        }

    }

    public void menu() {
        System.out.println("\nCRUD RMI: Livraria");
        System.out.println("1 - listar livros.  ");
        System.out.println("2 - adicionar livro.");
        System.out.println("3 - atualizar livro.");
        System.out.println("4 - remover livro.");
        System.out.println("9 - Menu.");
        System.out.println("0 - sair.");
    }

}
