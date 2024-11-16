package org.montengro.data;

import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.*;
import java.util.List;

/**
 * Clase para gestionar la serialización y deserialización de objetos en formato binario.
 *
 * @version 1.0
 */
public class Binario_Manager {
  private static final String DIRECTORIO = "Archivos/";

  /**
   * Escribe una lista de concesionarios en un archivo binario.
   *
   * @param archivo el nombre del archivo donde se guardará la lista.
   * @param concesionarioList la lista de concesionarios a serializar.
   */
  public static void escribirArchivoBinario(String archivo, List<Concesionario> concesionarioList) {
    ConsolaView view = new ConsolaView();
    ObjectOutputStream objectOutputStream = null;
    try {
      objectOutputStream = new ObjectOutputStream(new FileOutputStream(DIRECTORIO + archivo));
      objectOutputStream.writeObject(concesionarioList);
      view.mostrarMensaje("Se ha escrito el archivo " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Lee una lista de concesionarios desde un archivo binario.
   *
   * @param archivo el nombre del archivo desde donde se leerá la lista.
   * @return la lista de concesionarios deserializada.
   */
  public static List<Concesionario> leerArchivoBinario(String archivo) {
    ConsolaView view = new ConsolaView();
    ObjectInputStream inputStream = null;

    try {
      inputStream = new ObjectInputStream(new FileInputStream(DIRECTORIO + archivo));
      List<Concesionario> concesionarioList = (List<Concesionario>) inputStream.readObject();
      view.mostrarMensaje("Datos leídos de " + archivo + " en " + DIRECTORIO);
      return concesionarioList;
    } catch (FileNotFoundException e) {
      view.mostrarMensaje("El archivo " + archivo + " no se encontró en " + DIRECTORIO + ". Verifica la ruta y el nombre del archivo.");
      e.printStackTrace();
    } catch (IOException | ClassNotFoundException e) {
      view.mostrarMensaje("Ocurrió un error al leer el archivo " + archivo + " en " + DIRECTORIO);
      e.printStackTrace();
    }
    return null;
  }
}
