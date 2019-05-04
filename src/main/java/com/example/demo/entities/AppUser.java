package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {

	@JsonProperty("id")
	@Id @GeneratedValue
	private Long id;
	@JsonProperty("login")
	private String login; 
	@JsonProperty("password")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	List<AppRole> roles = new ArrayList<>();
}
