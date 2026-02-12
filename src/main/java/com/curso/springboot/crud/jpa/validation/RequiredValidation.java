package com.curso.springboot.crud.jpa.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<isRequired, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//return (value != null && !value.isBlank());
		return StringUtils.hasText(value);
			
		
	}

}
