package com.curso.springboot.crud.jpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.springboot.crud.jpa.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class isExistsDBValidator implements ConstraintValidator<isExistsDb, String> {
	
	@Autowired
	private ProductService productService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//return value != null && !productService.existsBySKU(value) ;
		 if(productService != null){
             return !productService.existsBySKU(value);
         }
         return true;
	}

}
