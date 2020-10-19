package com.grupo7.practico.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CantidadProductos {

  @EmbeddedId ProductoIdWrapper productoIdWrapper;

  Integer cantidad;
}
