package com.grupo7.practico.repository;

import com.grupo7.practico.model.ControlStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlStockRepository extends CrudRepository<ControlStock,Integer> {

}
