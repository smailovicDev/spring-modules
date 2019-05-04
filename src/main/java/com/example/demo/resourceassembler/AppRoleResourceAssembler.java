package com.example.demo.resourceassembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.controllers.AppRolesRestController;
import com.example.demo.entities.AppRole;
import com.example.demo.resources.AppRoleResource;

public class AppRoleResourceAssembler extends ResourceAssemblerSupport<AppRole, AppRoleResource> {

	public AppRoleResourceAssembler() {
		
		super(AppRolesRestController.class, AppRoleResource.class);
	}

	@Override
	public AppRoleResource toResource(AppRole appRole) {
		
		return createResourceWithId(appRole.getId(), appRole);
	}

	@Override
	protected AppRoleResource instantiateResource(AppRole appRole) {
		return new AppRoleResource(appRole);
	}
	
	

}
