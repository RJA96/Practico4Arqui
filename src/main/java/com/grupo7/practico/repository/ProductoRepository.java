package com.grupo7.practico.repository;

import com.grupo7.practico.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {
  @Query(
      value = "SELECT * FROM producto p "
          + "    Join cantidad_productos cp on p.id_producto = cp.producto_id_producto "
          + "GROUP BY id_producto, cp.cantidad "
          + "order by cp.cantidad DESC", nativeQuery = true)
  List<Producto> findMostBuy();
}
