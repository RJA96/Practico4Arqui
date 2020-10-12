package com.grupo7.practico.controller;

import com.grupo7.practico.controller.request.StockRequest;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ControlStockId;
import com.grupo7.practico.repository.ControlStockRepository;
import com.grupo7.practico.service.ControlStockService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlStock")
public class ControlStockController {

  private static Logger logger = LoggerFactory.getLogger(ControlStockController.class);

  private ControlStockService controlStockService;

  @Autowired
  public ControlStockController(ControlStockService controlStockService) {
    this.controlStockService = controlStockService;
  }

  @PostMapping
  @RequestMapping("/crearStock")
  public ResponseEntity<?> createStock(@RequestBody StockRequest stockRequest) {
    try {
      controlStockService.createStock(stockRequest.getProducto(), stockRequest.getCantidad());
      return ResponseEntity.status(HttpStatus.OK).body("Stock creado");
    } catch (Exception e) {
      logger.error("Error al crear controlStock", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Stock");
    }
  }

  @PutMapping
  @RequestMapping("/añadirStock")
  public ResponseEntity<?> addStock(@RequestBody StockRequest stockRequest) {
    try {
      controlStockService.addStock(stockRequest.getProducto(), stockRequest.getCantidad());
      return ResponseEntity.ok()
          .body("El stock del producto: " + stockRequest.getProducto() + " fue modificado");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("producto no encontrado para añadir stock");
    }
  }

  @GetMapping
  @RequestMapping("/getStock")
  public ResponseEntity<?> getStock() {
    try {
      return ResponseEntity.ok().body(controlStockService.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener los stock");
    }
  }

  @DeleteMapping
  @RequestMapping("/deleteStock")
  public ResponseEntity<?> deleteStock(@RequestParam Integer id) {
    try {
      controlStockService.deleteStock(id);
      return ResponseEntity.ok().body("Eliminado control Stock id: " + id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado");
    }
  }
}
