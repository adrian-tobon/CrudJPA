package com.curso.springboot.crud.jpa.entities;

import java.util.Objects;

import com.curso.springboot.crud.jpa.validation.isExistsDb;
import com.curso.springboot.crud.jpa.validation.isRequired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "{NotEmpty.product.name}")
	@Size(min=3, max = 20)
	private String name;
	
	@Column(name="sku")
	@isExistsDb
	@isRequired
	private String SKU;
	
	@NotNull(message = "{NotNull.product.price}")
	@Min(value = 500,message= "{Min.product.price}")
	private Integer price;
	
	//@NotBlank(message = "{NotBlank.product.description}")
	@isRequired
	private String description;
	
	
	public Product() {
		
	}
	
	public Product(String name, Integer price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, price);
	}
	
	

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && price == other.price;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", SKU=" + SKU + ", price=" + price + ", description="
				+ description + "{";
	}
	
	
	
	

}
