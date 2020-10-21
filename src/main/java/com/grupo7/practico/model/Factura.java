package com.grupo7.practico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Factura implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer idFactura;

  LocalDate fecha;

  Double monto;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  List<CantidadProductos> productosList;
}
