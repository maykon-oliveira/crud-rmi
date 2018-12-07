import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Servidor
 */
public interface Servidor extends Remote {

    public boolean addLivro(Livro livro) throws RemoteException;

    public boolean atualizar(Livro livro) throws RemoteException;
    
    public boolean delete(Livro livro) throws RemoteException, LivroNotFoundException;

    public List<Livro> pegarTodos(Livro livro) throws RemoteException;

}