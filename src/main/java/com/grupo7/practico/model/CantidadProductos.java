package com.grupo7.practico.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
Por las dudas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CantidadProductos {
  Producto producto;
  Integer cantidad;
}
