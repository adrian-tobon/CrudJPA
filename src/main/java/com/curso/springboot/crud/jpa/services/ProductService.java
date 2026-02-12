package com.curso.springboot.crud.jpa.services;

import java.util.List;
import java.util.Optional;

import com.curso.springboot.crud.jpa.entities.Product;

public interface ProductService {
	
	List <Product> findAll();
	
	Optional<Product> findById(Long id);
	
	Product save(Product product);
	
	Optional<Product> update(Long id, Product product);
	
	Optional<Product> delete(Long id);
	
	boolean existsBySKU(String sku);
	
}
