package com.grupo7.practico.repository;

import com.grupo7.practico.model.Cliente;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

  @Query(value = "SELECT c.idCliente,c.nombre,SUM(cp.cantidad * p.precio) "
      + "FROM Cliente c "
      + "JOIN Factura f "
      + "JOIN CantidadProductos cp "
      + "JOIN Producto p "
      + "GROUP BY 1,2 "
      + "ORDER BY 2 DESC")
  Map<Cliente,Double> findAllByFacturasAndGastos();
}
