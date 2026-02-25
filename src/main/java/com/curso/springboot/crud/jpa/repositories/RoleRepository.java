package com.curso.springboot.crud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.crud.jpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>  {
	
	

}
