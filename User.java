package bci.core;

import java.util.LinkedList;
import java.io.Serializable;
import java.util.Collections;

public class User implements Serializable {
	
	private static final long serialVersionUID = 202501101348L;
    private int _id = 0;
    private final String _name;
    private final String _email;
    private UserStatus _status;
    private UserClassification _classification;

    private final LinkedList<Boolean> _returnHistory = new LinkedList<>();

    public User(String name, String email) {
    	_id ++;
        _name = name;
        _email = email;
        _status = UserStatus.ACTIVE;
        _classification = UserClassification.NORMAL;
    }

    public int getId() { return _id; }
    
    public String getName() { return _name; }
    
    public String getEmail() { return _email; }
    
    public UserStatus getStatus() { return _status; }
    
    public UserClassification getClassification() { return _classification; }

    public void suspend() { _status = UserStatus.SUSPENDED; }
    
    public void activate() { _status = UserStatus.ACTIVE; }

    public void recordReturn(boolean onTime) {
        _returnHistory.add(onTime);
        if (_returnHistory.size() > 5)  
            _returnHistory.removeFirst();
        updateClassification();
    }

    private void updateClassification() {
    	int onTime = Collections.frequency(_returnHistory, true);
    	int delays = Collections.frequency(_returnHistory, false);

        if (_returnHistory.size() >= 3 && delays >= 3) {
            _classification = UserClassification.FALTOSO;
        } else if (_returnHistory.size() >= 3 && onTime >= 3 && _classification == UserClassification.FALTOSO) {
            _classification = UserClassification.NORMAL;
        } else if (_returnHistory.size() >= 5 && onTime == 5) {
            _classification = UserClassification.CUMPRIDOR;
        } else {
            _classification = UserClassification.NORMAL;
        }
    }
}
