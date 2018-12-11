import java.net.MalformedURLException;
import java.rmi.*;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Cliente {

    private final Servidor servidor;
    private Scanner scanner;

    public Cliente(Servidor servidor) {
        System.out.println("Iniciando servidor...");
        this.servidor = servidor;
    }

    public static void main(String[] argv) {
        try {
            Servidor servidor = (Servidor) Naming.lookup("rmi://127.0.0.1/ServidorDB");
            Cliente cliente = new Cliente(servidor);

            try (Scanner in = new Scanner(System.in)) {
                cliente.setScanner(in);

                Short op = 0;

                cliente.menu();
                while (true) {
                    System.out.print("\nEntre com a opção: ");
                    op = in.nextShort();

                    cliente.processarEscolha(op);
                }
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
        System.out.println("1 - Listar livros.  ");
        System.out.println("2 - Adicionar livro.");
        System.out.println("3 - Atualizar livro.");
        System.out.println("4 - Remover livro.");
        System.out.println("9 - Menu.");
        System.out.println("0 - Sair.");
    }

    public void processarEscolha(Short op) throws RemoteException {
        switch (op) {
        case 1:
            System.out.println("\nLivros na estante");
            System.out.println("=================");
            servidor.pegarTodos().forEach(System.out::println);
            break;
        case 2:
            System.out.println("\nENTRE COM AS INFORMAÇÕES");

            System.out.print("ISBN: ");
            String isbn = pegarEntradaDoTeclado();
            System.out.print("Nome: ");
            String nome = pegarEntradaDoTeclado();
            System.out.print("Autor: ");
            String autor = pegarEntradaDoTeclado();
            System.out.print("Quantidade: ");
            Integer qtd = scanner.nextInt();

            Livro livro = new Livro(isbn, nome, autor, qtd);

            System.out.println(servidor.addLivro(livro) ? "\nLivro salvo!" : "\nLivro já cadastrado!");
            break;
        case 3:
            System.out.println("\nAlterar quantidade do livro");
            System.out.print("Digite o ISBN: ");
            String isbl = pegarEntradaDoTeclado();
            try {
                Livro l = servidor.findByIsbn(isbl);
                System.out.print("Quantidade: ");
                Integer qtdd = scanner.nextInt();
                l.setQuantidade(qtdd);
                servidor.atualizar(l);
                System.out.println("\nLivro atualizado!");
            } catch (LivroNotFoundException e) {
                System.out.println("\nLivro não encontrado!");
            }
            break;
        case 4:
            System.out.print("Digite o ISBN: ");
            String isb = pegarEntradaDoTeclado();
            System.out.println(servidor.delete(isb) ? "\nLivro excluído!" : "\nLivro não encontrado!");
            break;
        case 9:
            menu();
            break;
        case 0:
            System.exit(0);
            break;
        default:
            menu();
            break;
        }
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private String pegarEntradaDoTeclado() {
        String entrada = scanner.nextLine();
        while (entrada.equalsIgnoreCase("")) {
            entrada = scanner.nextLine();
        }
        return entrada;
    }

}
