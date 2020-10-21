package com.grupo7.practico.repository;

import com.grupo7.practico.model.Cliente;
import com.grupo7.practico.model.Factura;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
  @Query(value = "Select * " +
      "from cliente c JOIN cliente_facturas cf on c.id_cliente = cf.cliente_id_cliente " +
      "where facturas_id_factura = :id",nativeQuery = true)
  Cliente findByFactura(@Param("id") Integer id);

  @Query(
      value =
          "SELECT c.id_cliente as id, c.nombre ,SUM(f.monto) AS suma "
              + "    FROM cliente c "
              + "        JOIN cliente_facturas cf on c.id_cliente = cf.cliente_id_cliente "
              + "    JOIN factura f on f.id_factura = cf.facturas_id_factura "
              + "    GROUP BY 1,2 "
              + "ORDER BY 3 DESC",
      nativeQuery = true)
  List<IReporte> findAllByFacturasAndGastos();
}
