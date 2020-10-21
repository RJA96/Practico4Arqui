package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ProductoIdWrapper;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ControlStockRepository;
import com.grupo7.practico.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Implementacion del servicio de ControlStock */
@Service
public class ControlStockServiceImpl implements ControlStockService {

  private ControlStockRepository controlStockRepository;

  private ProductoRepository productoRepository;

  @Autowired
  public ControlStockServiceImpl(
      ControlStockRepository controlStockRepository, ProductoRepository productoRepository) {
    this.controlStockRepository = controlStockRepository;
    this.productoRepository = productoRepository;
  }

  @Override
  public List<ControlStock> getAll() {
    return Lists.newArrayList(controlStockRepository.findAll());
  }

  /**
   * Para crear el stock primero revisa si existe el producto
   * @param producto
   * @param cantidad
   */
  @SneakyThrows
  @Override
  public void createStock(Integer producto, Integer cantidad) {
    Optional<Producto> productoOptional = productoRepository.findById(producto);
    if (productoOptional.isPresent()) {
      ControlStock controlStock =
          ControlStock.builder()
              .cantidadStock(cantidad)
              .productoIdWrapper(
                  ProductoIdWrapper.builder().producto(productoOptional.get()).build())
              .build();
      controlStockRepository.save(controlStock);
    } else {
      throw new NotFoundException("No existe el producto para crear Stock");
    }
  }

  /**
   * Para añadir stock primero revisa si existe
   * @param producto
   * @param cantidad
   */
  @SneakyThrows
  @Override
  public void addStock(Integer producto, Integer cantidad) {
    Optional<Producto> p = productoRepository.findById(producto);
    Optional<ControlStock> controlStockOptional =
        controlStockRepository.findById(
            ProductoIdWrapper.builder()
                .producto(p.get())
                .build());
    if (controlStockOptional.isPresent()) {
      ControlStock controlStockTemp = controlStockOptional.get();
      controlStockTemp.setCantidadStock(cantidad);
      controlStockRepository.save(controlStockTemp);
    } else {
      throw new NotFoundException("No existe el stock indicado");
    }
  }

  @SneakyThrows
  @Override
  public void deleteStock(Integer id) {
    Optional<Producto> p = productoRepository.findById(id);
    Optional<ControlStock> controlStockOptional =
        controlStockRepository.findById(
            ProductoIdWrapper.builder()
                .producto(p.get())
                .build());
    if (controlStockOptional.isPresent()) {
      controlStockRepository.delete(controlStockOptional.get());
    } else {
      throw new NotFoundException("No existe el stock indicado");
    }
  }
}
