import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * ServidorImple
 */
public class ServidorImple extends UnicastRemoteObject implements Servidor {

    private static final long serialVersionUID = 1L;

    public ServidorImple() throws RemoteException {
        System.out.println("Novo Servidor instanciado...");
    }

    @Override
    public boolean addLivro(Livro livro) throws RemoteException {
        return true;
    }
    
    @Override
    public boolean atualizar(Livro livro) throws RemoteException {
        return true;
    }
    
    @Override
    public boolean delete(Livro livro) throws RemoteException, LivroNotFoundException {
        return true;
    }
    
    @Override
    public List<Livro> pegarTodos(Livro livro) throws RemoteException {
        return null;
    }
    
}