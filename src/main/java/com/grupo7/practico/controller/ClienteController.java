package com.grupo7.practico.controller;

import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.service.ClienteService;
import java.util.List;
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
@RequestMapping("/clientes")
public class ClienteController {

  private static Logger logger = LoggerFactory.getLogger(ClienteController.class);
  private ClienteService clienteService;

  @Autowired
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @GetMapping("/getAll")
  public List<Cliente> all() {
    return clienteService.getAll();
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> add(@RequestBody Cliente cliente) {
    clienteService.addCliente(cliente);
    return ResponseEntity.status(HttpStatus.OK).body(cliente);
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody Cliente cliente) {
    try {
      clienteService.updateCliente(cliente);
      return ResponseEntity.status(HttpStatus.OK).body(cliente);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam Integer idCliente) {
    try {
      clienteService.deleteCliente(idCliente);
      return ResponseEntity.ok().body("Eliminado Cliente id: " + idCliente);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
  }
}
