package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Task;
import com.example.demo.services.TasksService;

@RestController
@CrossOrigin("*")
public class TasksRestController {
	
	@Autowired
	private TasksService tasksService;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks(){
		// PageRequest prq = PageRequest.of(0,3, Sort.by("taskName").descending());
		// we can use pageRequest object to send page to our user 
		// Page object provide many information about our page we can get the content of page by getContent()
		return new ResponseEntity<List<Task>>( this.tasksService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task saveTask( @RequestBody Task t ) {
		return this.tasksService.save(t);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> findById( @PathVariable("id") long idTask ) {
		
		Optional<Task> task = this.tasksService.findById(idTask);
		
		return task.isPresent() ? new ResponseEntity<>( task.get(), HttpStatus.OK)
				 : new ResponseEntity<>( null, HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask( @PathVariable("id") long idTask) {
		
		try{
			
		this.tasksService.deleteById(idTask);
		
		}catch( EmptyResultDataAccessException e ){}
	}
	
	
	
	
	
	

}
