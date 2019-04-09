package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entities.AppUser;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AppUsersService appUsersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser appUser = appUsersService.findByLogin(username);
		
		if(appUser == null ) throw new UsernameNotFoundException(" User Not Found !!! ");
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		appUser.getRoles().forEach(r ->{
			authorities.add( new SimpleGrantedAuthority( r.getRoleName()) );
		});
		User user = new User(appUser.getLogin(), appUser.getPassword(), authorities);
		
		return  user;
	}

}
