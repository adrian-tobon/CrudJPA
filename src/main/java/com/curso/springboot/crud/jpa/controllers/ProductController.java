package com.curso.springboot.crud.jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.crud.jpa.ProductValidator;
import com.curso.springboot.crud.jpa.entities.Product;
import com.curso.springboot.crud.jpa.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/*@Autowired
	private ProductValidator productValidator;*/

	/*
	 * private final ProductService productService2; public
	 * ProductController(ProductService productService2) { this.productService2 =
	 * productService2; }
	 */

	@GetMapping
	public List<Product> list() {
		return productService.findAll();
	}

	// path variable
	@GetMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable Long id) {

		Optional<Product> optProduct = productService.findById(id);
		if (optProduct.isPresent()) {
			return ResponseEntity.ok(optProduct.orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {

		// Product newProduct = productService.save(product);
		// return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
		
		//productValidator.validate(product, result);
		if(result.hasFieldErrors()) {
			return 	validation(result);		
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product updatedProduct, BindingResult result, @PathVariable Long id) {
		//productValidator.validate(updatedProduct, result);
		if(result.hasFieldErrors()) {
			return validation(result);		
		}
		
		Optional<Product> optProduct = productService.update(id, updatedProduct);
		if (optProduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(optProduct.orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Product> deletedProduct = productService.delete(id);
		if (deletedProduct.isPresent()) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String,String> errors = new HashMap<>();
		
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errors);
	}
}
