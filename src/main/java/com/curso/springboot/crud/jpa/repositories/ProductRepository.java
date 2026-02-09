package com.curso.springboot.crud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import com.curso.springboot.crud.jpa.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
