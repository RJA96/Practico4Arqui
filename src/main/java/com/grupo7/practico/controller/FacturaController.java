package com.grupo7.practico.controller;

import com.grupo7.practico.controller.request.FacturaClienteRequest;
import com.grupo7.practico.controller.request.FacturaClienteRequest2;
import com.grupo7.practico.model.Factura;
import com.grupo7.practico.service.FacturaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

/**
 * Controller de facturas
 */
@RestController
@RequestMapping("facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
  private FacturaService facturaService;

  @Autowired
  public FacturaController(FacturaService facturaService) {
    this.facturaService = facturaService;
  }

  @GetMapping("/getAll")
  public List<Factura> all() {
    return facturaService.getAll();
  }

  /**
   * Save de facturas para adaptarlo al front
   * @param factura
   * @return ResponseEntity
   */
  @PostMapping("/save")
  public ResponseEntity<?> add(@RequestBody FacturaClienteRequest factura) {
    facturaService.addFactura(factura.getFecha(),factura.getMonto(),factura.getCliente());
    return ResponseEntity.status(HttpStatus.OK).body(factura);
  }

  /**
   * Save real de facturas (por postman)
   * @param factura
   * @return ResponseEntity
   */
  @PostMapping("/saveAs")
  public ResponseEntity<?> add(@RequestBody FacturaClienteRequest2 factura) {
    facturaService.addFactura(factura.getFactura(),factura.getIdCliente());
    return ResponseEntity.status(HttpStatus.OK).body(factura);
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody Factura factura) {
   try{
     facturaService.updateFactura(factura);
     return ResponseEntity.status(HttpStatus.OK).body(factura);
   } catch (Exception e) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada");
   }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam Integer idFactura) {
    try{

      facturaService.deleteFactura(idFactura);
      return ResponseEntity.ok().body("Eliminado la factura id: " + idFactura);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada");
    }
  }

  /**
   * Endpoint para devolver las facturas de una fecha especifica
   * @param date
   * @return facturas por fecha
   */
  @GetMapping("/getByFecha")
  public ResponseEntity<?> getByFecha(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    List<Factura> factura = facturaService.getByFecha(date);
    return ResponseEntity.status(HttpStatus.OK).body(factura);
  }
}
