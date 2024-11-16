package org.montengro.controller;

import org.montengro.data.Binario_Manager;
import org.montengro.data.Json_Manager;
import org.montengro.data.Xml_Manager;
import org.montengro.model.Coche;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gestionar las operaciones de los concesionarios y coches.
 * Proporciona métodos para iniciar la aplicación, insertar concesionarios y coches,
 * listar concesionarios y coches, exportar datos a diferentes formatos y guardar datos.
 *
 * @version 1.0
 */
public class ConcesionarioController {
  private static final List<Concesionario> CONCESIONARIO_LIST = new ArrayList<>();
  private static final String DIRECTORIO = "Archivos/";
  private final ConsolaView view = new ConsolaView();

  /**
   * Inicia la aplicación, mostrando el menú principal y gestionando las opciones seleccionadas por el usuario.
   */
  public void iniciar() {
    crearDirectorioSiNoExiste();
    cargarDatos();
    Scanner scanner = new Scanner(System.in);
    int opcion;
    do {
      opcion = view.mostrarMenu();

      switch (opcion) {
        case 1:
          insertarConcesionario();
          break;
        case 2:
          insertarCoche();
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

  /**
   * Crea el directorio para almacenar los archivos si no existe.
   */
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

  /**
   * Carga los datos de los concesionarios desde un archivo binario.
   */
  private void cargarDatos() {
    File archivo = new File(DIRECTORIO + "concesionarios.dat");
    List<Concesionario> datosCargados = archivo.exists() ? Binario_Manager.leerArchivoBinario("concesionarios.dat") : null;

    if (datosCargados != null) {
      CONCESIONARIO_LIST.addAll(datosCargados);
    } else {
      view.mostrarMensaje("El archivo 'concesionarios.dat' no existe. Se creará uno nuevo al guardar los datos.");
    }
  }

  /**
   * Inserta un nuevo concesionario en la lista.
   */
  private void insertarConcesionario() {
    String nombre = view.leerTexto("Ingrese el nombre del concesionario:");
    String descripcion = view.leerTexto("Ingrese la descripción del concesionario:");
    Concesionario concesionario = new Concesionario(nombre, descripcion, new ArrayList<>());
    CONCESIONARIO_LIST.add(concesionario);
    view.mostrarMensaje("Concesionario añadido.");
  }

  /**
   * Inserta un nuevo coche en un concesionario existente.
   */
  private void insertarCoche() {
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

  /**
   * Lista todos los concesionarios y sus coches.
   */
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

  /**
   * Exporta los datos de los concesionarios a formatos JSON y XML.
   */
  private void exportarFormatos() {
    Json_Manager.serializarAJson(CONCESIONARIO_LIST, "concesionarios.json");
    Xml_Manager.serializarAXml(CONCESIONARIO_LIST, "concesionarios.xml");
  }

  /**
   * Guarda los datos de los concesionarios en un archivo binario.
   */
  private void guardarDatos() {
    Binario_Manager.escribirArchivoBinario("concesionarios.dat", CONCESIONARIO_LIST);
  }
}
