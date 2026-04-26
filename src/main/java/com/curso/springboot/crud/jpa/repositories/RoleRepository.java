package com.curso.springboot.crud.jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.crud.jpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>  {
	
	Optional<Role> findByName(String name);
	
	

}
