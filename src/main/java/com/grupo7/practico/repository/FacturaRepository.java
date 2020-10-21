package com.grupo7.practico.repository;

import com.grupo7.practico.model.Factura;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repositorio de facturas */
@Repository
public interface FacturaRepository extends CrudRepository<Factura, Integer> {

  List<Factura> findByFecha(LocalDate fecha);
}
