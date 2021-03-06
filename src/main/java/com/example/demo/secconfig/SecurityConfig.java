package com.example.demo.secconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/error").permitAll()
		.antMatchers("/roles/**").hasAnyAuthority("SUPER_ADMIN","ADMIN")
		.antMatchers("/users/**").hasAnyAuthority("SUPER_ADMIN","ADMIN");
		http.formLogin().defaultSuccessUrl("/tasks");
		http.formLogin().disable();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter( new JWTAuthenticationFilter( authenticationManager() )  );
		http.addFilterBefore( new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class  );
		
	}
	
	

}
