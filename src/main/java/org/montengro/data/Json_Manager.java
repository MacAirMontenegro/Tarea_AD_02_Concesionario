package org.montengro.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase para gestionar la serialización y deserialización de objetos en formato JSON.
 * Utiliza Jackson para la conversión y serialización de objetos.
 *
 * @version 1.0
 */
public class Json_Manager {
  private static final ObjectMapper OBJECT_MAPPER = inicializarObjectMapper();
  private static final String DIRECTORIO = "Archivos/";

  /**
   * Inicializa y configura el ObjectMapper para la serialización.
   *
   * @return una instancia configurada de ObjectMapper.
   */
  private static ObjectMapper inicializarObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  /**
   * Serializa una lista de concesionarios a un archivo JSON.
   *
   * @param listaConcesionarios la lista de concesionarios a serializar.
   * @param archivo el nombre del archivo donde se guardará la serialización.
   */
  public static void serializarAJson(List<Concesionario> listaConcesionarios, String archivo) {
    ConsolaView view = new ConsolaView();
    try {
      OBJECT_MAPPER.writeValue(new File(DIRECTORIO + archivo), listaConcesionarios);
      view.mostrarMensaje("Se han exportado los datos a " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Deserializa una lista de concesionarios desde un archivo JSON.
   *
   * @param archivo el nombre del archivo desde donde se leerá la lista.
   * @return la lista de concesionarios deserializada.
   */
  public static List<Concesionario> deserializarDeJson(String archivo) {
    try {
      return OBJECT_MAPPER.readValue(new File(DIRECTORIO + archivo), OBJECT_MAPPER.getTypeFactory().
       constructCollectionType(List.class, Concesionario.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
