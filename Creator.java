package bci.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Creator implements Serializable {
		
	private static final long serialVersionUID = 202501101348L;
    private final String _name;
    private final Set<Work> _works = new HashSet<>();

    public Creator(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    void addWork(Work work) {
        _works.add(work);
    }

    void removeWork(Work work) {
        _works.remove(work);
    }

    public boolean hasWorks() {
        return !_works.isEmpty();
    }

    public Set<Work> works() {
        return Collections.unmodifiableSet(_works);
    }
}
