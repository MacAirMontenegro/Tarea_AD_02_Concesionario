package org.montengro.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un concesionario de coches con un nombre, descripción y una lista de coches.
 *
 * <p>Esta clase utiliza anotaciones de Lombok para generar automáticamente
 * métodos getter, setter, toString, equals y hashCode, así como los constructores con
 * y sin argumentos. Además, emplea anotaciones de Jackson para definir cómo se
 * serializa en formato XML.
 * </p>
 *
 * @see Coche
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "concesionario")
public class Concesionario implements Serializable {

  /**
   * Nombre del concesionario.
   */
  private String nombre;

  /**
   * Descripción del concesionario.
   */
  private String descripcion;

  /**
   * Lista de coches disponibles en el concesionario.
   * La anotación JacksonXmlElementWrapper se usa para definir cómo se serializa la lista en XML.
   */
  @JacksonXmlElementWrapper(localName = "coches", useWrapping = false)
  private List<Coche> coches = new ArrayList<>();
}
