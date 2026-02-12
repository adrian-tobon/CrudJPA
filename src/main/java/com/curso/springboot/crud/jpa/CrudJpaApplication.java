package com.curso.springboot.crud.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({	
	@PropertySource(value="classpath:messages.properties", encoding="UTF-8")
})
public class CrudJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudJpaApplication.class, args);
	}

}
