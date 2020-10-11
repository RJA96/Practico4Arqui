package com.grupo7.practico.controller;

import com.grupo7.practico.controller.request.StockRequest;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ControlStockId;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ControlStockRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlStock")
public class ControlStockController {
  private ControlStockRepository controlStockRepository;

  @Autowired
  public ControlStockController(ControlStockRepository controlStockRepository) {
    this.controlStockRepository = controlStockRepository;
  }

  @PutMapping
  @RequestMapping("/añadirStock")
  public ResponseEntity<?> addStock(@RequestBody StockRequest stockRequest) {
    if (ObjectUtils.isEmpty(
        controlStockRepository.findById(stockRequest.getProducto().getIdProducto()))) {
      ControlStock controlStock =
          ControlStock.builder()
              .cantidadStock(stockRequest.getCantidad())
              .controlStockId(ControlStockId.builder().producto(stockRequest.getProducto()).build())
              .build();
      controlStockRepository.save(controlStock);
      return ResponseEntity.status(HttpStatus.OK).body(controlStock);
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
