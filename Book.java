package bci.core;

import java.io.Serializable;
import java.util.List;

public class Book extends Work implements Serializable {

	private static final long serialVersionUID = 202501101348L;
	
    private final String _isbn;  // ISBN do livro
    
    private final List<Creator> _authors;  // Lista de autores (criadores)

    public Book(String title, int price, Category category, int copies, String isbn, List<Creator> authors) {
        super(title, price, category, copies);  // Chama o construtor da classe Work
        _isbn = isbn;
        _authors = authors;
        // Adiciona os autores à obra
        for (Creator author : authors) {
            addCreator(author);
        }
    }

    public String getIsbn() {
        return _isbn;
    }

    public List<Creator> getAuthors() {
        return _authors;
    }
}
