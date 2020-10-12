package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

  private ProductoRepository productoRepository;

  @Autowired
  public ProductoServiceImpl(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public List<Producto> getAll() {
    return Lists.newArrayList(productoRepository.findAll());
  }

  @Override
  public void addProducto(Producto producto) {
    productoRepository.save(producto);
  }

  @Override
  @SneakyThrows
  public void updateProducto(Producto producto) {
    if (productoRepository.findById(producto.getIdProducto()).isPresent()) {
      productoRepository.save(producto);
    } else {
      throw new NotFoundException("No existe Producto a actualizar");
    }
  }

  @Override
  @SneakyThrows
  public void deleteProducto(Integer id) {
    Optional<Producto> productoOptional = productoRepository.findById(id);
    if (productoOptional.isPresent()) {
      productoRepository.delete(productoOptional.get());
    } else {
      throw new NotFoundException("No existe el Producto indicado");
    }
  }
}
