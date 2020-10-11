package com.grupo7.practico.service;

import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.Producto;
import java.util.List;


public interface ControlStockService {
  List<ControlStock> getAll();
  void createStock(Producto producto, Integer cantidad);
  void addStock(Producto producto, Integer cantidad);
  void deleteStock(Integer id);
}
