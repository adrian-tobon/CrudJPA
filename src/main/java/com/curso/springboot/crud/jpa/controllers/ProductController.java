package com.curso.springboot.crud.jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.crud.jpa.entities.Product;
import com.curso.springboot.crud.jpa.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

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
	public ResponseEntity<Product> save(@RequestBody Product product) {

		// Product newProduct = productService.save(product);
		// return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updatedProduct) {
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
}
