package com.example.demo.resources;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.example.demo.entities.AppUser;
import com.example.demo.resourceassembler.AppRoleResourceAssembler;

import lombok.Getter;

@Relation(value="user", collectionRelation="users")
public class AppUserResource extends ResourceSupport {

	private static final AppRoleResourceAssembler appRoleResourceAssembler = new AppRoleResourceAssembler();

	@Getter
	private final String login;
	@Getter
	private final String password;
	@Getter
	private List<AppRoleResource> roles;
	// private final List<AppRole> roles ;

	public AppUserResource(AppUser appUser) {

		this.login = appUser.getLogin();
		this.password = appUser.getPassword();
		this.roles = appRoleResourceAssembler.toResources(appUser.getRoles());
		// this.roles = appUser.getRoles();

	}
}
