package com.grupo7.practico.repository;

import com.grupo7.practico.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Repositorio de CantidadProducto */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

  /**
   * Query para obtener cliente por factura
   *
   * @param id Factura
   * @return cliente
   */
  @Query(
      value =
          "Select * "
              + "from cliente c JOIN cliente_facturas cf on c.id_cliente = cf.cliente_id_cliente "
              + "where facturas_id_factura = :id",
      nativeQuery = true)
  Cliente findByFactura(@Param("id") Integer id);

  /**
   * Query para obtener el listado de clientes segun sus gastos
   *
   * @return
   */
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
