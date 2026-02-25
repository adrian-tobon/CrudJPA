package com.curso.springboot.crud.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.crud.jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
