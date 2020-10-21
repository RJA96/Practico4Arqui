package com.grupo7.practico.repository;

import com.grupo7.practico.model.ControlStock;
import com.grupo7.practico.model.ProductoIdWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repositorio de ControlStock */
@Repository
public interface ControlStockRepository extends CrudRepository<ControlStock, ProductoIdWrapper> {}
