package org.montengro.data;

import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.*;
import java.util.List;

public class Binario_Manager {
  private static final String DIRECTORIO = "Archivos/";

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
