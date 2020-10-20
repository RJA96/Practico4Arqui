package com.grupo7.practico.controller;

import com.grupo7.practico.model.Producto;
import com.grupo7.practico.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
  private ProductoService productoService;

  @Autowired
  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/getAll")
  public List<Producto> all() {
    return productoService.getAll();
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> add(@RequestBody Producto producto) {
    productoService.addProducto(producto);
    return ResponseEntity.status(HttpStatus.OK).body(producto);
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody Producto producto) {
    try {
      productoService.updateProducto(producto);
      return ResponseEntity.status(HttpStatus.OK).body(producto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam Integer idProducto) {
    try {
      productoService.deleteProducto(idProducto);
      return ResponseEntity.ok().body("Eliminado Producto id: " + idProducto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
  }

  @GetMapping("/getByMostBuy")
  public Producto getByMostBuy() {
    return productoService.findByMostBuy();
  }

}
