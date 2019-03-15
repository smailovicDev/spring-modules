package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Task;
import com.example.demo.services.TasksService;

@RestController
public class TasksRestController {
	
	@Autowired
	private TasksService tasksService;
	
	@GetMapping("/tasks")
	public List<Task> getAllTasks(){
		
		return this.tasksService.findAll();
	}
	
	@PostMapping("/tasks")
	public Task saveTask( @RequestBody Task t ) {
		return this.tasksService.save(t);
	}
	
	@GetMapping("/tasks/{id}")
	public Optional<Task> findById( @PathVariable long idTask ) {
		return this.tasksService.findById(idTask);
	}

}
