package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Task;
import com.example.demo.services.TasksService;

@SpringBootApplication
public class SpringBootJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner start( TasksService ts ) {
		
		return args -> {
			ts.save( new Task(null, "T1", "Hello T1" ));
			ts.save( new Task(null, "T2", "Hello T2" ));
			ts.save( new Task(null, "T3", "Hello T3" ));
			ts.save( new Task(null, "T4", "Hello T4" ));
			ts.save( new Task(null, "T5", "Hello T5" ));
		};
	}

}