import java.rmi.Naming;

public class IniciaServidor {
    public static void main(String argv[]) {
        try {
            System.out.println("Arrancando servidor...");
            Naming.rebind("ServidorDB", new ServidorImple());
        } catch (Exception e) {
            System.out.println("Ocorreu um problema no arranque do servidor.\n" + e.toString());
        }
    }

}
