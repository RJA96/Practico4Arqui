package com.grupo7.practico.utils;

import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ControlStockId;
import com.grupo7.practico.model.Producto;
import com.grupo7.practico.repository.ClienteRepository;
import com.grupo7.practico.repository.ControlStockRepository;
import com.grupo7.practico.repository.FacturaRepository;
import com.grupo7.practico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private ClienteRepository clienteRepository;
  private FacturaRepository facturaRepository;
  private ProductoRepository productoRepository;
  private ControlStockRepository controlStockRepository;

  @Autowired
  public DataLoader(
      ClienteRepository clienteRepository,
      FacturaRepository facturaRepository,
      ProductoRepository productoRepository,
      ControlStockRepository controlStockRepository) {
    this.clienteRepository = clienteRepository;
    this.productoRepository = productoRepository;
    this.facturaRepository = facturaRepository;
    this.controlStockRepository = controlStockRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    createClientes();
    createProducto();
    createControlStock();
    createFacturas();
  }

  private void createClientes() {
    clienteRepository.save(Cliente.builder().nombre("Jon Campillo").build());
    clienteRepository.save(Cliente.builder().nombre("Mireia Real").build());
    clienteRepository.save(Cliente.builder().nombre("Rosa Bernabeu").build());
    clienteRepository.save(Cliente.builder().nombre("Martin Mateu").build());
    clienteRepository.save(Cliente.builder().nombre("Sabrina Mari").build());
    clienteRepository.save(Cliente.builder().nombre("Maribel Ramos").build());
    clienteRepository.save(Cliente.builder().nombre("Antia Montes").build());
    clienteRepository.save(Cliente.builder().nombre("Irati Cobos").build());
    clienteRepository.save(Cliente.builder().nombre("Amador Sevilla").build());
    clienteRepository.save(Cliente.builder().nombre("Mari Luna").build());
  }

  private void createProducto() {
    productoRepository.save(Producto.builder().nombre("Jabon").precio(180.00).build());
    productoRepository.save(Producto.builder().nombre("Leche").precio(40.50).build());
    productoRepository.save(Producto.builder().nombre("Galletas").precio(20.50).build());
    productoRepository.save(Producto.builder().nombre("Pizza").precio(150.0).build());
    productoRepository.save(Producto.builder().nombre("Jamon").precio(90.00).build());
    productoRepository.save(Producto.builder().nombre("Quesoo").precio(200.0).build());
    productoRepository.save(Producto.builder().nombre("Plato").precio(70.00).build());
    productoRepository.save(Producto.builder().nombre("Tenedor").precio(30.00).build());
    productoRepository.save(Producto.builder().nombre("Vaso").precio(80.00).build());
    productoRepository.save(Producto.builder().nombre("Cerveza").precio(100.40).build());
  }

  private void createControlStock() {
    Producto productoTemp =
        Producto.builder().idProducto(11).nombre("Jabon").precio(180.00).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(50)
            .build());
    productoTemp = Producto.builder().idProducto(12).nombre("Leche").precio(40.50).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(20)
            .build());
    productoTemp = Producto.builder().idProducto(13).nombre("Galletas").precio(20.50).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(30)
            .build());
    productoTemp = Producto.builder().idProducto(14).nombre("Pizza").precio(150.0).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(35)
            .build());
    productoTemp = Producto.builder().idProducto(15).nombre("Jamon").precio(90.00).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(25)
            .build());
    productoTemp = Producto.builder().idProducto(16).nombre("Quesoo").precio(200.0).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(40)
            .build());
    productoTemp = Producto.builder().idProducto(17).nombre("Plato").precio(70.00).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(25)
            .build());
    productoTemp = Producto.builder().idProducto(18).nombre("Tenedor").precio(30.00).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(15)
            .build());
    productoTemp = Producto.builder().idProducto(19).nombre("Vaso").precio(80.00).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(10)
            .build());
    productoTemp = Producto.builder().idProducto(20).nombre("Cerveza").precio(100.40).build();
    controlStockRepository.save(
        ControlStock.builder()
            .controlStockId(ControlStockId.builder().producto(productoTemp).build())
            .cantidadStock(2)
            .build());
  }

  private void createFacturas() {

  }
}
