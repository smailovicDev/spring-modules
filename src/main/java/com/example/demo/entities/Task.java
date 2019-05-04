package com.example.demo.entities;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Task {
	
	@Id @GeneratedValue
	private Long idTask;
	
	@JsonProperty("taskName")
	private String taskName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("serId")
	private long serId;
	
	@JsonProperty("dateCreation")
	private ZonedDateTime dateCreation; 
	
	@JsonProperty("dateFin")
	private ZonedDateTime dateFin; 
	
	@JsonProperty("status")
	private boolean status; 
	
	@JsonProperty("pourcent")
	private double pourcent; 
	
	
	
	

}
