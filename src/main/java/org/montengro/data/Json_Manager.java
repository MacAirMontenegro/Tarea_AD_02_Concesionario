package org.montengro.data;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Json_Manager {
  private static final ObjectMapper OBJECT_MAPPER = inicializarObjectMapper();
  private static final String DIRECTORIO = "Archivos/";


  private static ObjectMapper inicializarObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  public static void serializarAJson(List<Concesionario> listaConcesionarios, String archivo) {
    ConsolaView view = new ConsolaView();
    try {
      OBJECT_MAPPER.writeValue(new File(DIRECTORIO + archivo), listaConcesionarios);
      view.mostrarMensaje("Se han exportado los datos a " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

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
