package bci.core;

import java.io.Serializable;

public class Dvd extends Work implements Serializable {

	private static final long serialVersionUID = 202501101348L;
	
    private final Creator _director;  // Realizador (um criador)
    
    private final String _igacNumber;  // Número de registo IGAC

    public Dvd(String title, int price, Category category, int copies, String igacNumber, Creator director) {
        super(title, price, category, copies);  // Chama o construtor da classe Work
        _igacNumber = igacNumber;
        _director = director;
        // Adiciona o realizador como criador da obra
        addCreator(director);
    }

    public String getIgacNumber() {
        return _igacNumber;
    }

    public Creator getDirector() {
        return _director;
    }
}
