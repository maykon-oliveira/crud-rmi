/**
 * LivroNotFoundException
 */
public class LivroNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public LivroNotFoundException() {
        super("Livro não encontrado no banco.");
    }

}
