import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class IniciaServidor {
    
    public static void main(String argv[]) {
        try {
            System.out.println("Arrancando servidor...");
            Naming.rebind("ServidorDB", new ServidorImple());
        } catch (MalformedURLException | RemoteException e) {
            System.out.println("Ocorreu um problema no arranque do servidor.\n" + e.toString());
        }
    }

}
