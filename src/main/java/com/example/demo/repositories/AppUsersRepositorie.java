package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.AppUser;

public interface AppUsersRepositorie extends JpaRepository<AppUser, Long> {
	
	public AppUser findByLogin( String login);

}
