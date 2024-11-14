package org.montengro.controller;

import org.montengro.model.Coche;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConcesionarioController {
  private static final List<Concesionario> CONCESIONARIO_LIST = new ArrayList<>();
  private final ConsolaView view = new ConsolaView();

  public void iniciar() {
    crearDirectorioSiNoExiste();
    cargarDatos();
    Scanner scanner = new Scanner(System.in);
    int opcion;
    do {
      opcion = view.mostrarMenu();

      switch (opcion) {
        case 1:
          insertarConcesionario(scanner);
          break;
        case 2:
          insertarCoche(scanner);
          break;
        case 3:
          listarConcesionariosYCoches();
          break;
        case 4:
          exportarJsonYXml();
          break;
        case 5:
          guardarDatos();
          view.mostrarMensaje("Datos guardados. Saliendo de la aplicación.");
          break;
        default:
          view.mostrarMensaje("Opción no válida. Por favor, intente de nuevo.");
      }
    } while (opcion != 5);
    scanner.close();
  }

  private void insertarConcesionario(Scanner scanner) {
    String nombre = view.leerTexto("Ingrese el nombre del concesionario:");
    String descripcion = view.leerTexto("Ingrese la descripción del concesionario:");
    Concesionario concesionario = new Concesionario(nombre, descripcion, new ArrayList<>());
    CONCESIONARIO_LIST.add(concesionario);
    view.mostrarMensaje("Concesionario añadido.");
  }

  private void insertarCoche(Scanner scanner) {
    Coche coche = new Coche();
    if (CONCESIONARIO_LIST.isEmpty()) {
      view.mostrarMensaje("No hay concesionarios disponibles. Primero añada un concesionario.");
      return;
    }
    coche.setMatricula(view.leerTexto("Ingrese la matrícula del coche:"));
    coche.setFechaMatriculacion(LocalDate.parse(view.leerTexto("Ingrese la fecha de matriculación (yyyy-MM-dd):")));
    coche.setKilometros(view.leerEntero("Ingrese el número de kilómetros:"));

    view.mostrarMensaje("Seleccione el concesionario donde desea insertar el coche:");
    for (int i = 0; i < CONCESIONARIO_LIST.size(); i++) {
      view.mostrarMensaje((i + 1) + ". " + CONCESIONARIO_LIST.get(i).getNombre());
    }

    int seleccion = view.leerEntero("Elija una opción:");
    if (seleccion > 0 && seleccion <= CONCESIONARIO_LIST.size()) {
      Concesionario concesionario = CONCESIONARIO_LIST.get(seleccion - 1);
      concesionario.getCoches().add(coche);
      view.mostrarMensaje("Coche añadido al concesionario " + concesionario.getNombre() + ".");
    } else {
      view.mostrarMensaje("Selección no válida.");
    }


  }

  private void listarConcesionariosYCoches() {
    System.out.println("estas en crear lista");
  }

  private void exportarJsonYXml() {
    System.out.println("Exportando las cosas");
  }

  private void guardarDatos() {
    System.out.println("datos guardados");
  }

  private void cargarDatos() {
  }

  private void crearDirectorioSiNoExiste() {
  }
}
