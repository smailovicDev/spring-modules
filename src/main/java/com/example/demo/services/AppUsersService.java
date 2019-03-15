package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entities.AppUser;
import com.example.demo.repositories.AppUsersRepositorie;

@Component
public class AppUsersService {
	
	@Autowired
	AppUsersRepositorie appUsersRepositorie;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public <S extends AppUser> S save(S entity) {
		entity.setPassword( bCryptPasswordEncoder.encode( entity.getPassword() ) );
		return appUsersRepositorie.save(entity);
	}

	public Optional<AppUser> findById(Long id) {
		
		return appUsersRepositorie.findById(id);
	}

	public boolean existsById(Long id) {
		return appUsersRepositorie.existsById(id);
	}

	public void deleteById(Long id) {
		appUsersRepositorie.deleteById(id);
	}

	public void delete(AppUser entity) {
		appUsersRepositorie.delete(entity);
	}

	public List<AppUser> findAll() {
		return appUsersRepositorie.findAll();
	}

	public AppUser findByLogin(String login) {
		return appUsersRepositorie.findByLogin(login);
	}
	
	
	

}
