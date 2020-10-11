package com.grupo7.practico.controller;

import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.repository.ClienteRepository;
import java.util.Optional;
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
@RequestMapping("/clientes")
public class ClienteController {

  private static Logger logger = LoggerFactory.getLogger(ClienteController.class);
  private ClienteRepository clienteRepository;

  @Autowired
  public ClienteController(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @GetMapping("/getAll")
  public Iterable<Cliente> all() {
    return this.clienteRepository.findAll();
  }

  @PostMapping(value = "/save")
  public ResponseEntity<?> add(@RequestBody Cliente cliente) {
      return ResponseEntity.status(HttpStatus.OK).body(this.clienteRepository.save(cliente));
  }

  @PutMapping("/update")
  public Cliente update(@RequestBody Cliente cliente) {
    if (!ObjectUtils.isEmpty(clienteRepository.findById(cliente.getIdCliente()))) {
      return this.clienteRepository.save(cliente);
    } else {
      return null;
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam Integer idCliente) {
    if (!ObjectUtils.isEmpty(clienteRepository.findById(idCliente))) {
      try {
        Optional<Cliente> clienteTemp = clienteRepository.findById(idCliente);
        clienteRepository.delete(clienteTemp.get());
        return ResponseEntity.status(HttpStatus.OK).body(clienteTemp.get());
      } catch (Exception e) {
        logger.error("Error al eliminar cliente", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al eliminar cliente");
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
  }
}
