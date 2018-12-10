import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

/**
 * Servidor
 */
public interface Servidor extends Remote {

    public boolean addLivro(Livro livro) throws RemoteException;

    public void atualizar(Livro livro) throws RemoteException, LivroNotFoundException;
    
    public boolean delete(String isbn) throws RemoteException;

    public List<Livro> pegarTodos() throws RemoteException;
    
    public Optional<Livro> findByIsbn(String isbn) throws RemoteException;

}
