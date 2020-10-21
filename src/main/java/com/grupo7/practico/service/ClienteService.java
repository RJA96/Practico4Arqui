package com.grupo7.practico.service;

import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.model.Factura;
import com.grupo7.practico.repository.IReporte;
import java.util.List;
import java.util.Map;

public interface ClienteService {
  List<Cliente> getAll();

  void addCliente(Cliente cliente);

  void updateCliente(Cliente cliente);

  void deleteCliente(Integer id);

  List<IReporte> getReporte();
}
