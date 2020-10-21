package com.grupo7.practico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlStock implements Serializable {

  @EmbeddedId
  ProductoIdWrapper productoIdWrapper;

  Integer cantidadStock;
}
