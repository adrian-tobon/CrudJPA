package com.curso.springboot.crud.jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.crud.jpa.entities.Product;
import com.curso.springboot.crud.jpa.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	@Override
	public Optional<Product> delete(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		optProduct.ifPresent(productDb -> productRepository.delete(productDb));
		return optProduct;

	}

	@Transactional
	@Override
	public Optional<Product> update(Long id, Product product) {
		Optional<Product> optProduct = productRepository.findById(id);
		if (optProduct.isPresent()) {
			Product productDb = optProduct.orElseThrow();
			productDb.setName(product.getName());
			productDb.setPrice(product.getPrice());
			productDb.setDescription(product.getDescription());
			productRepository.save(productDb);

			return Optional.of(productDb);
		} else {
			return Optional.empty();
		}
	}

}
