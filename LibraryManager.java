package bci.core;

import bci.core.exception.*;
import java.io.*;

/**
 * A classe fachada. Representa o gerente da aplicação. Ela gerencia a biblioteca atual e
 * funciona como a interface entre a camada central e a camada de interação com o usuário.
 */
public class LibraryManager {

  /** O objeto que faz todo o trabalho real. */
  /* A biblioteca atual */
  private Library _library;

  /**
   * Construtor que inicializa a biblioteca.
   */
  public LibraryManager() {
    // Inicializa uma biblioteca vazia
    this._library = new Library();
  }

  /**
   * Construtor com a inicialização da biblioteca.
   * 
   * @param library o objeto Library a ser gerenciado por este gerente.
   */
  public LibraryManager(Library library) {
    this._library = library;
  }

  /**
   * Salva o estado serializado da aplicação no arquivo associado à biblioteca atual.
   *
   * @throws FileNotFoundException se por algum motivo o arquivo não puder ser criado ou aberto. 
   * @throws MissingFileAssociationException se a biblioteca atual não tiver um arquivo associado.
   * @throws IOException se houver algum erro ao serializar o estado da rede para o disco.
   **/
  public void save() throws MissingFileAssociationException, FileNotFoundException, IOException {
    if (_library == null || _library.getFile() == null) {
      // Lançando MissingFileAssociationException com apenas uma mensagem de erro
      throw new MissingFileAssociationException();
    }
    // Serializa a biblioteca atual no arquivo associado
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(_library.getFile()))) {
      out.writeObject(_library);
    }
  }

  /**
   * Salva o estado serializado da aplicação no arquivo especificado. A biblioteca atual está
   * associada a este arquivo.
   *
   * @param filename o nome do arquivo.
   * @throws FileNotFoundException se por algum motivo o arquivo não puder ser criado ou aberto.
   * @throws MissingFileAssociationException se a biblioteca atual não tiver um arquivo associado.
   * @throws IOException se houver algum erro ao serializar o estado da rede para o disco.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_library == null) {
      // Lançando MissingFileAssociationException com apenas uma mensagem de erro
      throw new MissingFileAssociationException();
    }
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
      out.writeObject(_library);
    }
    // Opcionalmente, define o nome do arquivo como a associação da biblioteca
    _library.setFile(filename);
  }

  /**
   * Carrega o estado previamente serializado da aplicação e o define como a biblioteca atual.
   *
   * @param filename nome do arquivo contendo o estado serializado da aplicação
   *        para carregar.
   * @throws UnavailableFileException se o arquivo especificado não existir ou houver
   *         um erro ao processá-lo.
   **/
  public void load(String filename) throws UnavailableFileException {
    File file = new File(filename);
    if (!file.exists()) {
      // Lançando UnavailableFileException com apenas uma mensagem de erro
      throw new UnavailableFileException("O arquivo não existe ou não é acessível.");
    }

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
      _library = (Library) in.readObject();
      _library.setFile(filename);  // Garante que o arquivo está associado à biblioteca.
    } catch (IOException | ClassNotFoundException e) {
      // Lançando UnavailableFileException com a exceção original como causa
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * Lê um arquivo de entrada de texto e inicializa a biblioteca atual (que deve estar vazia)
   * com as entidades do domínio representadas no arquivo de importação.
   *
   * @param datafile nome do arquivo de entrada de texto
   * @throws ImportFileException se ocorrer algum erro durante o processamento do
   * arquivo de importação.
   **/
  public void importFile(String datafile) throws ImportFileException {
    try {
        if (datafile != null && !datafile.isEmpty()) {
            _library.importFile(datafile);
        }
    } catch (IOException e) {
        // Lançando ImportFileException com mensagem e causa
        throw new ImportFileException("Erro durante a importação do arquivo: " + datafile, e);
    } catch (UnrecognizedEntryException e) {
        // Lançando ImportFileException com mensagem e causa
        throw new ImportFileException("Erro durante a importação do arquivo: " + datafile, e);
    }
}



  /**
   * Retorna a biblioteca atual.
   */
  public Library getLibrary() {
    return _library;
  }

  /**
   * Define a biblioteca atual.
   */
  public void setLibrary(Library library) {
    this._library = library;
  }
}
