package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Task;
import com.example.demo.repositories.TasksRepositorie;

@Component
public class TasksService {

	@Autowired 
	private TasksRepositorie  tasksRepositorie;

	public <S extends Task> S save(S entity) {
		return tasksRepositorie.save(entity);
	}

	public List<Task> findAll() {
		return tasksRepositorie.findAll();
	}

	public Optional<Task> findById(Long id) {
		return tasksRepositorie.findById(id);
	}

	public void deleteById(Long id) {
		tasksRepositorie.deleteById(id);
	}

	public void delete(Task entity) {
		tasksRepositorie.delete(entity);
	}
	
	
	
}
