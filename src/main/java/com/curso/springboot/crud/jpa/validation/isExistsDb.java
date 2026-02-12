package com.curso.springboot.crud.jpa.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = isExistsDBValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface isExistsDb {
	
	String message() default "{IsExistsDb.product.sku}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
