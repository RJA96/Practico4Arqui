package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ControlStockId;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ControlStockRepository;
import com.grupo7.practico.repository.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlStockServiceImpl implements ControlStockService {

  private ControlStockRepository controlStockRepository;

  private ProductoRepository productoRepository;

  @Autowired
  public ControlStockServiceImpl(
      ControlStockRepository controlStockRepository,
      ProductoRepository productoRepository) {
    this.controlStockRepository = controlStockRepository;
    this.productoRepository = productoRepository;
  }

  @Override
  public List<ControlStock> getAll() {
    return Lists.newArrayList(controlStockRepository.findAll());
  }

  @SneakyThrows
  @Override
  public void createStock(Producto producto, Integer cantidad) {
    Optional<Producto> productoOptional = productoRepository.findById(producto.getIdProducto());
    if (productoOptional.isPresent()) {
      ControlStock controlStock =
          ControlStock.builder()
              .cantidadStock(cantidad)
              .controlStockId(ControlStockId.builder().producto(productoOptional.get()).build())
              .build();
      controlStockRepository.save(controlStock);
    }
    else {
      throw new NotFoundException("No existe el producto para crear Stock");
    }


  }

  @SneakyThrows
  @Override
  public void addStock(Producto producto, Integer cantidad) {
    Optional<ControlStock> controlStockOptional = controlStockRepository.findById(ControlStockId.builder().producto(
        Producto.builder().idProducto(producto.getIdProducto()).build()).build());
    if (controlStockOptional.isPresent()) {
      ControlStock controlStockTemp = controlStockOptional.get();
      controlStockTemp.setCantidadStock(cantidad);
      controlStockRepository.save(controlStockTemp);
    }
    else {
      throw new NotFoundException("No existe el stock indicado");
    }
  }

  @SneakyThrows
  @Override
  public void deleteStock(Integer id) {
    Optional<ControlStock> controlStockOptional = controlStockRepository.findById(ControlStockId.builder().producto(
        Producto.builder().idProducto(id).build()).build());
    if (controlStockOptional.isPresent()) {
      controlStockRepository.delete(controlStockOptional.get());
    }
    else {
      throw new NotFoundException("No existe el stock indicado");
    }
  }
}
