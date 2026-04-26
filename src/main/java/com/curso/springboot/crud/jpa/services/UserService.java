package com.curso.springboot.crud.jpa.services;

import java.util.List;

import com.curso.springboot.crud.jpa.entities.User;

public interface UserService {
	
	List<User> findAll();
	
	User save(User user);

	boolean existByUsername(String username);

}
