package org.montengro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Representa un coche con su matrícula, fecha de matriculación y kilometraje.
 * Esta clase es serializable y puede ser convertida a XML.
 *
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "coche")
public class Coche implements Serializable {
  /**
   * La matrícula del coche.
   */
  private String matricula;
  /**
   * La fecha de matriculación del coche.
   * Formato: yyyy-MM-dd
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaMatriculacion;
  /**
   * El kilometraje del coche.
   */
  private int kilometros;
}

