package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.AppRole;

public interface AppRolesRepositorie extends JpaRepository<AppRole, Long> {

}
