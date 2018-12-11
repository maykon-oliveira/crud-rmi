import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ServidorImple
 */
public class ServidorImple extends UnicastRemoteObject implements Servidor {

    private static final long serialVersionUID = 1L;
    private final List<Livro> livros;

    public ServidorImple() throws RemoteException {
        System.out.println("Novo Servidor instanciado...");
        livros = new ArrayList<>();
    }

    @Override
    public boolean addLivro(Livro livro) throws RemoteException {
        if (livros.contains(livro)) {
            return false;
        } else {
            livros.add(livro);
            return true;
        }        
    }

    @Override
    public void atualizar(Livro livro) throws RemoteException {
        Integer index = livros.indexOf(livro);
        if (index != -1) {
            Livro l = livros.get(index);
            l.setQuantidade(livro.getQuantidade());
        }
    }
    
    @Override
    public boolean delete(String isbn) throws RemoteException {
        Optional<Livro> livro = livros.stream().filter(l -> l.getIsbn().equalsIgnoreCase(isbn)).findAny();
        if (livro.isPresent()) {
            livros.remove(livro.get());
            return true;
        }
        return false;
    }
    
    @Override
    public List<Livro> pegarTodos() throws RemoteException {
        return livros;
    }
    
    @Override
    public Livro findByIsbn(String isbn) throws RemoteException, LivroNotFoundException {
        Optional<Livro> livro = livros.stream().filter(l -> l.getIsbn().equalsIgnoreCase(isbn))
            .findAny();
        
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new LivroNotFoundException();
    };
    
}
