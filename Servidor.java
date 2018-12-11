import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

/**
 * Servidor
 */
public interface Servidor extends Remote {

    public boolean addLivro(Livro livro) throws RemoteException;

    public void atualizar(Livro livro) throws RemoteException;
    
    public boolean delete(String isbn) throws RemoteException;

    public List<Livro> pegarTodos() throws RemoteException;
    
    public Livro findByIsbn(String isbn) throws RemoteException, LivroNotFoundException;

}
