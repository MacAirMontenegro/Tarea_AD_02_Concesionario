package org.montengro;

import org.montengro.controller.ConcesionarioController;

/**
 * Clase principal que inicia la aplicación del concesionario.
 */
public class Main {

  /**
   * Método principal que sirve como punto de entrada de la aplicación.
   *
   * @param args argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    // Crear una instancia del controlador del concesionario
    ConcesionarioController controlador = new ConcesionarioController();

    // Iniciar el controlador
    controlador.iniciar();
  }
}

