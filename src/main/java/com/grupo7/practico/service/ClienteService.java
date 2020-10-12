package com.grupo7.practico.service;

import com.grupo7.practico.model.Cliente;
import java.util.List;

public interface ClienteService {
  List<Cliente> getAll();

  void addCliente(Cliente cliente);

  void updateCliente(Cliente cliente);

  void deleteCliente(Integer id);
}