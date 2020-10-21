package com.grupo7.practico.repository;

import com.grupo7.practico.model.CantidadProductos;
import com.grupo7.practico.model.ProductoIdWrapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Repositorio de CantidadProducto */
@Repository
public interface CantidadProductosRepository
    extends CrudRepository<CantidadProductos, ProductoIdWrapper> {}
