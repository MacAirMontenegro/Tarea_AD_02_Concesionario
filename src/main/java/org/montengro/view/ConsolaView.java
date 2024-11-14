package org.montengro.view;

import java.util.Scanner;

/**
 * La clase ConsolaView proporciona una interfaz de consola para interactuar con el usuario.
 * Ofrece métodos para mostrar menús, leer entradas de texto y enteros, y mostrar mensajes.
 */
public class ConsolaView {

  // Scanner para leer la entrada del usuario desde la consola
  private Scanner scanner = new Scanner(System.in);

  /**
   * Muestra el menú principal y lee la opción seleccionada por el usuario.
   *
   * @return la opción seleccionada por el usuario
   */
  public int mostrarMenu() {
    System.out.println("\nMenú:");
    System.out.println("1. Insertar concesionario");
    System.out.println("2. Insertar coche");
    System.out.println("3. Listar concesionarios y coches");
    System.out.println("4. Exportar a .json, .xml y .data");
    System.out.println("5. Guardar datos y salir");
    System.out.print("Elija una opción: ");
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Consumir la línea restante
    return opcion;
  }

  /**
   * Lee una entrada de texto del usuario después de mostrar un mensaje.
   *
   * @param mensaje el mensaje para mostrar al usuario
   * @return la entrada de texto del usuario
   */
  public String leerTexto(String mensaje) {
    System.out.println(mensaje);
    return scanner.nextLine();
  }

  /**
   * Lee una entrada entera del usuario después de mostrar un mensaje.
   *
   * @param mensaje el mensaje para mostrar al usuario
   * @return la entrada entera del usuario
   */
  public int leerEntero(String mensaje) {
    System.out.println(mensaje);
    int entero = scanner.nextInt();
    scanner.nextLine(); // Consumir la línea restante
    return entero;
  }

  /**
   * Muestra un mensaje en la consola.
   *
   * @param mensaje el mensaje para mostrar al usuario
   */
  public void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
  }
}
