package com.grupo7.practico.model;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer idCliente;

  String nombre;

  @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
  List<Factura> facturas = new ArrayList<>();

  public void addFactura(Factura factura) {
    this.facturas.add(factura);
  }

}
