package com.curso.springboot.crud.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.crud.jpa.entities.User;
import com.curso.springboot.crud.jpa.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {		
		return (List<User>)userRepository.findAll();
	}
	
	@Transactional
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
