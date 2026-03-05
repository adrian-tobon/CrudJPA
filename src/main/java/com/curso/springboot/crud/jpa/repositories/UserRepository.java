package com.curso.springboot.crud.jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.crud.jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	boolean existsByUsername(String username);
	
	Optional<User> findByUsername(String username);

}
