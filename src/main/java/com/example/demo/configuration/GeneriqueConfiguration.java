package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.Task;
import com.example.demo.services.AppRolesService;
import com.example.demo.services.AppUsersService;
import com.example.demo.services.TasksService;

@Configuration
public class GeneriqueConfiguration {
	
	@Bean
	public CommandLineRunner start( TasksService ts, AppRolesService r, AppUsersService u ) {
		
		return args -> {
			ts.save( new Task(null, "T1", "Hello T1" ));
			ts.save( new Task(null, "T2", "Hello T2" ));
			ts.save( new Task(null, "T3", "Hello T3" ));
			ts.save( new Task(null, "T4", "Hello T4" ));
			ts.save( new Task(null, "T5", "Hello T5" ));
			List<AppRole> l = new ArrayList<>();
			Stream.of("ADMIN", "SUPER_ADMIN", "USER" ).forEach(s -> {
				l.add(r.save( new AppRole(null, s)));
			});
			
			u.save( new AppUser(null, "smail", "1234",l));
			l.remove(1);
			u.save( new AppUser(null, "admin", "1234",l));
			l.remove(0);
			u.save( new AppUser(null, "user", "1234",l));
				
		};
	}
	
	@Bean
	public BCryptPasswordEncoder getBcryptPassworEncoder() {
		return new BCryptPasswordEncoder();
	}

}
