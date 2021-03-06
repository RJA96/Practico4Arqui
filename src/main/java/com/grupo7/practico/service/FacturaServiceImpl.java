package com.grupo7.practico.service;

import com.google.common.collect.Lists;
import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.model.Factura;
import com.grupo7.practico.repository.ClienteRepository;
import com.grupo7.practico.repository.FacturaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Implementacion del servicio de Facturas */
@Service
public class FacturaServiceImpl implements FacturaService {

  private FacturaRepository facturaRepository;
  private ClienteRepository clienteRepository;

  @Autowired
  public FacturaServiceImpl(
      FacturaRepository facturaRepository, ClienteRepository clienteRepository) {
    this.facturaRepository = facturaRepository;
    this.clienteRepository = clienteRepository;
  }

  @Override
  public List<Factura> getAll() {
    return Lists.newArrayList(facturaRepository.findAll());
  }

  @Override
  @SneakyThrows
  public void addFactura(Factura factura, Integer cliente) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(cliente);
    if (clienteOptional.isPresent()) {
      Cliente clientetemp = clienteOptional.get();
      clientetemp.getFacturas().add(factura);
      clienteRepository.save(clientetemp);
    } else {
      throw new NotFoundException("No existe el Cliente indicado");
    }
  }

  @Override
  @SneakyThrows
  public void addFactura(LocalDate fecha, Double monto, Integer cliente) {
    Optional<Cliente> clienteOptional = clienteRepository.findById(cliente);
    if (clienteOptional.isPresent()) {
      Cliente clientetemp = clienteOptional.get();
      clientetemp.getFacturas().add(Factura.builder().fecha(fecha).monto(monto).build());
      clienteRepository.save(clientetemp);
    } else {
      throw new NotFoundException("No existe el Cliente indicado");
    }
  }

  @Override
  @SneakyThrows
  public void updateFactura(Factura factura) {
    Optional<Factura> facturaOptional = facturaRepository.findById(factura.getIdFactura());
    if (facturaOptional.isPresent()) {
      facturaRepository.save(factura);
    } else {
      throw new NotFoundException("No existe la Factura indicada");
    }
  }

  /**
   * Para eliminar primero se elimina de la Lista del cliente q posea la factura
   *
   * @param idFactura
   */
  @Override
  @SneakyThrows
  public void deleteFactura(Integer idFactura) {
    Optional<Factura> facturaOptional = facturaRepository.findById(idFactura);
    if (facturaOptional.isPresent()) {
      Cliente clienteOptional = clienteRepository.findByFactura(idFactura);
      clienteOptional.removeFactura(facturaOptional.get());
      clienteRepository.save(clienteOptional);
      facturaRepository.delete(facturaOptional.get());
    } else {
      throw new NotFoundException("No existe la factura Indicada");
    }
  }

  @Override
  public List<Factura> getByFecha(LocalDate fecha) {
    return facturaRepository.findByFecha(fecha);
  }
}
