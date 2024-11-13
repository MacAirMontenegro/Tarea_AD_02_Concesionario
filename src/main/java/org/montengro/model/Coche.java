package org.montengro.model;

// Importaciones necesarias para el funcionamiento de la clase

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa un modelo de coche con atributos básicos como la matrícula,
 * la fecha de matriculación y el número de kilómetros recorridos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "coche")
public class Coche implements Serializable {

  /**
   * Número de matrícula del coche.
   */
  private String matricula;

  /**
   * Fecha de matriculación del coche en formato 'yyyy-MM-dd'.
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaMatriculacion;

  /**
   * Kilómetros recorridos por el coche.
   */
  private int kilometros;
}
