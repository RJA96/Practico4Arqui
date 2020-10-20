package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ProductoIdWrapper;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ControlStockRepository;
import com.grupo7.practico.repository.ProductoRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

  private ControlStockRepository controlStockRepository;

  private ProductoRepository productoRepository;

  @Autowired
  public ProductoServiceImpl(
      ProductoRepository productoRepository, ControlStockRepository controlStockRepository) {
    this.productoRepository = productoRepository;
    this.controlStockRepository = controlStockRepository;
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
    Optional<ControlStock> controlStockOptional =
        controlStockRepository.findById(
            ProductoIdWrapper.builder()
                .producto(Producto.builder().idProducto(id).build())
                .build());
    if (productoOptional.isPresent()) {
      if (controlStockOptional.isPresent()) {
        controlStockRepository.delete(controlStockOptional.get());
      }
      productoRepository.delete(productoOptional.get());
    } else {
      throw new NotFoundException("No existe el Producto indicado");
    }
  }

  @Override
  public Producto findByMostBuy() {
    List<Producto> productoList = productoRepository.findMostBuy();
    if (!productoList.isEmpty()) {
      return productoRepository.findMostBuy().get(0);
    }
    return null;
  }
}
