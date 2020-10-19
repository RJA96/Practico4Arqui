package com.grupo7.practico.service;

import com.grupo7.practico.model.Producto;
import java.util.List;

public interface ProductoService {
  List<Producto> getAll();

  void addProducto(Producto producto);

  void updateProducto(Producto producto);

  void deleteProducto(Integer id);

  Producto findByMostBuy();
}
