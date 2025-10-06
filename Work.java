package bci.core;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;


public abstract class Work implements Serializable {
	
	private static final long serialVersionUID = 202501101348L;
	
    private int _id = 0;
    
    private final String _title;
    
    private final int _price;
    
    private final Category _category;
    
    private int _copies;

    private final Set<Creator> _creators = new HashSet<>();

    protected Work(String title, int price, Category category, int copies) {
        _id ++;
        _title = title;
        _price = price;
        _category = category;
        _copies = copies;
    }

    public int getId() { return _id; }
    
    public String getTitle() { return _title; }
    
    public int getPrice() { return _price; }
    
    public Category getCategory() { return _category; }
    
    public int getCopies() { return _copies; }

    public void setCopies(int copies) { _copies = copies; }

    public Set<Creator> getCreators() { return _creators; }

    protected void addCreator(Creator c) {
        if (_creators.add(c)) {
            c.addWork(this);
        }
    }

    protected void removeCreator(Creator c) {
        if (_creators.remove(c)) {
            c.removeWork(this);
        }
    }
}
