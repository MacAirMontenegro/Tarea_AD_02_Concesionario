package org.montengro.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "concesionario")
public class Concesionario implements Serializable {
  private String nombre;
  private String DESCRIPCION;
  @JacksonXmlElementWrapper(localName = "coches",useWrapping = false)
  private List<Coche>coches=new ArrayList<>();
}
