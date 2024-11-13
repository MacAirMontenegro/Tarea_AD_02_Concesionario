package org.montengro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "coche")
public class Coche implements Serializable {
  private String matricula;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaMatriculacion;
  private int kilometros;
}
