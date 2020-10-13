package com.grupo7.practico.service;


import com.grupo7.practico.model.Factura;
import java.util.List;


public interface FacturaService {

  List<Factura> getAll();

  void addFactura(Factura factura, Integer cliente);

  void updateFactura(Factura factura);

  void deleteFactura(Integer idFactura);

}
