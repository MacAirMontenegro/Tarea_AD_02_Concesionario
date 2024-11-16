package org.montengro.data;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.montengro.model.Concesionario;
import org.montengro.view.ConsolaView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase para gestionar la serialización de objetos a formato XML.
 * Utiliza Jackson para la conversión y serialización de objetos.
 *
 * @version 1.0
 */
public class Xml_Manager {
  private static final XmlMapper XML_MAPPER = inicializarXMLMaper();
  private static final String DIRECTORIO = "Archivos/";

  /**
   * Inicializa y configura el XmlMapper para la serialización.
   *
   * @return una instancia configurada de XmlMapper.
   */
  private static XmlMapper inicializarXMLMaper() {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    xmlMapper.registerModule(new JavaTimeModule());
    return xmlMapper;
  }

  /**
   * Serializa una lista de concesionarios a un archivo XML.
   *
   * @param concesionarioList la lista de concesionarios a serializar.
   * @param archivo el nombre del archivo donde se guardará la serialización.
   */
  public static void serializarAXml(List<Concesionario> concesionarioList, String archivo) {
    ConsolaView view = new ConsolaView();
    try {
      XML_MAPPER.writeValue(new File(DIRECTORIO + archivo), concesionarioList);
      view.mostrarMensaje("Se han exportado los datos a " + archivo + " en " + DIRECTORIO);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
