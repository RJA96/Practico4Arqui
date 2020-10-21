package com.grupo7.practico.service;


import com.grupo7.practico.model.Factura;
import java.time.LocalDate;
import java.util.List;

/** Servicio para la logica de Facturas */
public interface FacturaService {

  List<Factura> getAll();
  void addFactura(Factura factura1, Integer cliente);
  void addFactura(LocalDate fecha, Double monto, Integer cliente);

  void updateFactura(Factura factura);

  void deleteFactura(Integer idFactura);

  List<Factura> getByFecha(LocalDate fecha);
}
