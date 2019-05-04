package com.example.demo.resources;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.example.demo.entities.AppRole;

import lombok.Getter;

@Relation(value="role", collectionRelation="roles")
public class AppRoleResource extends ResourceSupport {

	@Getter
	private String roleName;

	public AppRoleResource(AppRole role) {
		this.roleName = role.getRoleName();
	}

}
