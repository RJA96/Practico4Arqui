package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

  private ClienteRepository clienteRepository;

  @Autowired
  public ClienteServiceImpl(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @Override
  public List<Cliente> getAll() {
    return Lists.newArrayList(clienteRepository.findAll());
  }

  @Override
  public void addCliente(Cliente cliente) {
    clienteRepository.save(cliente);
  }

  @Override
  @SneakyThrows
  public void updateCliente(Cliente cliente) {
    if (clienteRepository.findById(cliente.getIdCliente()).isPresent()) {
      clienteRepository.save(cliente);
    } else {
      throw new NotFoundException("No existe Cliente a actualizar");
    }
  }

  @Override
  @SneakyThrows
  public void deleteCliente(Integer id) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(id);
    if (clienteOptional.isPresent()) {
      clienteRepository.delete(clienteOptional.get());
    } else {
      throw new NotFoundException("No existe el Cliente indicado");
    }
  }
}
