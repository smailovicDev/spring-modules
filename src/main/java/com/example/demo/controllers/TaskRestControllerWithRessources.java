package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Task;
import com.example.demo.services.TasksService;

/**
 * 
 * @author Smail ghatrif
 *
 */

// in this rest controller we goon to test Spring HATEOAS  it's a good feature of spring
// help us to expose also the resources from our API to make the front end work with non hardcoded links
@RestController
public class TaskRestControllerWithRessources {
	
	@Autowired
	private TasksService tasksService;
	
	@GetMapping("/hatoasTasks")
	public Resources<Resource<Task>> getAllTasks(){
		
		PageRequest prq = PageRequest.of(0,3, Sort.by("taskName").descending());
		
		Page<Task> tasks = this.tasksService.findAll(prq);
		
		System.out.println( tasks);
		
		Resources<Resource<Task>> recentResources = Resources.wrap(tasks);
		recentResources.add(
		// It work fine like this:
				
		//new Link("http://localhost:8080/tasks", "tasks")
				
		// but i dont think it's a good idea to hardcode the link in the controller we look for something more easy
		// for this reason spring hatoas provid us the ControllerLinkBuilder this pretty good controller help us to generate links from 
		// controllers class and also from methode like this 
				
		ControllerLinkBuilder.linkTo(TaskRestControllerWithRessources.class).slash("tasks").withRel("tasksUrl")
		
//		 for a method we can do it like this
//		 
//		ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(TaskRestControllerWithRessources.class).getAllTasks())
//		.withRel("tasksUrl")
//		
//		 in the previouse example spring taks the value in the path of mapping annotation 
		
		);
		return recentResources;
	}
	
	// this function retun Page of tasks with many information and it taks as parameter the page, page size and sort field 
	// we get all this params from request then we create PageRequest Object 
	@GetMapping("/pageTasks")
	public Page<Task> getTasksByPage( @RequestParam("p") int page,
			@RequestParam("ps") int pageSize, @RequestParam("sF")String sortField){
		
		PageRequest prq = PageRequest.of( page, pageSize, Sort.by(sortField).descending());
		
		Page<Task> tasks = this.tasksService.findAll(prq);
		
		return tasks;
	}
	

	
	
	

}
