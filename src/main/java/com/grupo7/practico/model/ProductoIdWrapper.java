package com.grupo7.practico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

/** Utilizado para embeber el id de producto para FK */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoIdWrapper implements Serializable {
  @OneToOne @MapsId Producto producto;
}
