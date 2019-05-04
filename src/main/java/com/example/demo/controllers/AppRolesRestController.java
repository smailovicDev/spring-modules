package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AppRole;
import com.example.demo.services.AppRolesService;


@RestController
@RequestMapping("roles")
public class AppRolesRestController {
	
	
	@Autowired
	AppRolesService appRolesService;
	
	@GetMapping()
	public List<AppRole> getAppUsers(){
		return this.appRolesService.findAll();
	}
	@GetMapping("/{id}")
	public Optional<AppRole> getOne( @PathVariable long id){
		return this.appRolesService.findById(id);
	}

}
