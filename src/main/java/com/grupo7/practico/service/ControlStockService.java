package com.grupo7.practico.service;

import com.grupo7.practico.model.ControlStock;
import java.util.List;

/** Servicio para la logica de ControlStock */
public interface ControlStockService {
  List<ControlStock> getAll();

  void createStock(Integer producto, Integer cantidad);

  void addStock(Integer Producto, Integer cantidad);

  void deleteStock(Integer id);
}
