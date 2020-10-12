package com.grupo7.practico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer idFactura;

  LocalDate fecha;

  @OneToMany List<CantidadProductos> productosList;
}
