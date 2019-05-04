package com.example.demo.resourceassembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.controllers.AppUsersRestController;
import com.example.demo.entities.AppUser;
import com.example.demo.resources.AppUserResource;

public class AppUserResourceAssembler extends ResourceAssemblerSupport<AppUser, AppUserResource> {

	public AppUserResourceAssembler( ) {
		super(AppUsersRestController.class , AppUserResource.class);
	}

	@Override
	public AppUserResource toResource(AppUser appUser) {
		
		return createResourceWithId(appUser.getId(), appUser);
	}

	@Override
	protected AppUserResource instantiateResource(AppUser appUser) {	
		return new AppUserResource(appUser);
	}

	
}
