package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AppUser;
import com.example.demo.services.AppUsersService;

@RestController
public class AppUsersRestController {
	
	
	@Autowired
	AppUsersService appUsersService;
	
	@GetMapping("users")
	public List<AppUser> getAppUsers(){
		return this.appUsersService.findAll();
	}
	@GetMapping("/users/{id}")
	public Optional<AppUser> getOne( @PathVariable long id){
		return this.appUsersService.findById(id);
	}

}
