package bci.core;

import java.io.*;
import bci.core.exception.UnrecognizedEntryException;
// FIXME import classes

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202501101348L;
  	private int _nextWorkId;
  	private int _nextUserId;
  	private boolean _modified;
  	
  	public Library() {
  		_nextWorkId = 1;
  		_nextUserId = 1;
  		
  	}
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define more methods
  	

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
	  		MyParser parser = new MyParser(this);
	        parser.parseFile(filename);
		}
    //FIXME implement method
}
