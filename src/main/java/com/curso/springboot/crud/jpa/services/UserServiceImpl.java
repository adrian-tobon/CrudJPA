package com.curso.springboot.crud.jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.crud.jpa.entities.Role;
import com.curso.springboot.crud.jpa.entities.User;
import com.curso.springboot.crud.jpa.repositories.RoleRepository;
import com.curso.springboot.crud.jpa.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {		
		return (List<User>)userRepository.findAll();
	}
	
	@Transactional
	@Override
	public User save(User user) {
		
		Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");		
		List<Role> roles = new ArrayList<>();
		
		optionalRoleUser.ifPresentOrElse(roles::add,() -> System.out.println("Rol no existe en la DB"));
				
		if(user.isAdmin()) {
			Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");			
			optionalRoleAdmin.ifPresentOrElse(roles::add,() -> System.out.println("Rol no existe en la DB"));			
		}
		
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));		
		return userRepository.save(user);
	}

}
