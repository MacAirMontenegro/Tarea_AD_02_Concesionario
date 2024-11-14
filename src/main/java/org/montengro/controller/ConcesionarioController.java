package org.montengro.controller;

import org.montengro.data.Binario_Manager;
import org.montengro.data.Json_Manager;
import org.montengro.model.Coche;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConcesionarioController {
  private static final List<Concesionario> CONCESIONARIO_LIST = new ArrayList<>();
  private static final String DIRECTORIO = "Archivos/";
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
          exportarFormatos();
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

  private void crearDirectorioSiNoExiste() {
    File directorio = new File(DIRECTORIO);
    if (directorio.exists()) {
      return;
    }
    if (directorio.mkdir()) {
      view.mostrarMensaje("Directorio '" + DIRECTORIO + "' creado en: " + directorio.getAbsolutePath());
    } else {
      view.mostrarMensaje("No se pudo crear el directorio '" + DIRECTORIO + "'.");
    }
  }

  private void cargarDatos() {
    File archivo = new File(DIRECTORIO + "concesionarios.dat");
    if (archivo.exists()) {
      List<Concesionario> datosCargados = Binario_Manager.leerArchivoBinario("concesionarios.dat");
    }
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
    if (CONCESIONARIO_LIST.isEmpty()) {
      view.mostrarMensaje("No hay concesionarios disponibles.");
      return;
    }
    CONCESIONARIO_LIST.forEach(concesionario -> {
      view.mostrarMensaje("Concesionario: " + concesionario.getNombre());
      view.mostrarMensaje("Descripción: " + concesionario.getDescripcion());
      if (concesionario.getCoches().isEmpty()) {
        view.mostrarMensaje("No hay coches en este concesionario.");
        return;
      }
      concesionario.getCoches().forEach(coche -> {
        view.mostrarMensaje("  Coche: " + coche.getMatricula());
        view.mostrarMensaje("    Fecha de matriculación: " + coche.getFechaMatriculacion());
        view.mostrarMensaje("    Kilómetros: " + coche.getKilometros());
      });
    });

  }

  private void exportarFormatos() {
    Json_Manager.serializarAJson(CONCESIONARIO_LIST, "concesionarios.json");
  }

  private void guardarDatos() {
    System.out.println("datos guardados");
  }
}
