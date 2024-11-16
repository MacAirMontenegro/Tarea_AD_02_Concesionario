package org.montengro.view;

import java.util.Scanner;

/**
 * Clase que representa la vista de consola para interactuar con el usuario.
 * Proporciona métodos para mostrar menús, leer entradas y mostrar mensajes.
 *
 * @version 1.0
 */
public class ConsolaView {

  private Scanner scanner = new Scanner(System.in);

  /**
   * Muestra el menú principal y lee la opción seleccionada por el usuario.
   *
   * @return la opción seleccionada por el usuario.
   */
  public int mostrarMenu() {
    String[] opciones = {
     "1. Insertar concesionario",
     "2. Insertar coche",
     "3. Listar concesionarios y coches",
     "4. Exportar a .json, .xml",
     "5. Guardar datos y salir"
    };

    mostarEnCuadro("Menú Principal", opciones);
    System.out.print("Elija una opción: ");
    int opcion = scanner.nextInt();
    scanner.nextLine();
    return opcion;
  }

  /**
   * Muestra un cuadro con un título y una lista de opciones.
   *
   * @param titulo   el título del cuadro.
   * @param opciones las opciones a mostrar en el cuadro.
   */
  public void mostarEnCuadro(String titulo, String[] opciones) {
    int maxLength = titulo.length();
    for (String opcion : opciones) {
      if (opcion.length() > maxLength) {
        maxLength = opcion.length();
      }
    }

    int ancho = maxLength + 4;
    String borde = "*".repeat(ancho);

    System.out.println("\n" + borde);
    System.out.println("*" + " ".repeat((ancho - titulo.length()) / 2) + titulo
     + " ".repeat((ancho - titulo.length() + 8) / 3) + "*");
    System.out.println(borde);

    for (String opcion : opciones) {
      System.out.println("* " + opcion + " ".repeat(ancho - opcion.length() - 3) + "*");
    }

    System.out.println(borde);
  }

  /**
   * Lee una cadena de texto introducida por el usuario.
   *
   * @param mensaje el mensaje a mostrar al usuario antes de leer la entrada.
   * @return la cadena de texto introducida por el usuario.
   */
  public String leerTexto(String mensaje) {
    System.out.println(mensaje);
    return scanner.nextLine();
  }

  /**
   * Lee un número entero introducido por el usuario.
   *
   * @param mensaje el mensaje a mostrar al usuario antes de leer la entrada.
   * @return el número entero introducido por el usuario.
   */
  public int leerEntero(String mensaje) {
    System.out.println(mensaje);
    int entero = scanner.nextInt();
    scanner.nextLine();
    return entero;
  }

  /**
   * Muestra un mensaje en la consola.
   *
   * @param mensaje el mensaje a mostrar.
   */
  public void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
  }
}
