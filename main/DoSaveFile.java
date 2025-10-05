package bci.app.main;

import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
// FIXME add more imports if needed

import java.io.IOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<LibraryManager> {

  DoSaveFile(LibraryManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  @Override
  protected final void execute() {
    // FIXME implement command and create a local Form
	  try {
	      _receiver.save();
	    } 
	    catch (MissingFileAssociationException e) {
	      Form form = new Form();
	      form.addStringField("filename", Prompt.newSaveAs());
	      form.parse();
	      String filename = form.stringField("filename");

	      try {
	        _receiver.saveAs(filename);
	      } 
	      catch (IOException | MissingFileAssociationException e2) {
	        _display.popup("Erro: não foi possível guardar o ficheiro \"" + filename + "\".");
	      }
	    } 
	    catch (IOException e) {
	      _display.popup("Erro: não foi possível guardar o ficheiro atual.");
	    }
	    }
}
