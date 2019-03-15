package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.AppRole;
import com.example.demo.repositories.AppRolesRepositorie;

@Component
public class AppRolesService {
	
	
	@Autowired
	AppRolesRepositorie appRolesRepositorie;

	public <S extends AppRole> S save(S entity) {
		return appRolesRepositorie.save(entity);
	}

	public Optional<AppRole> findById(Long id) {
		return appRolesRepositorie.findById(id);
	}

	public boolean existsById(Long id) {
		return appRolesRepositorie.existsById(id);
	}

	public void deleteById(Long id) {
		appRolesRepositorie.deleteById(id);
	}

	public void delete(AppRole entity) {
		appRolesRepositorie.delete(entity);
	}

	public List<AppRole> findAll() {
		return appRolesRepositorie.findAll();
	}
	
	
	

}
