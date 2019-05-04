package com.example.demo.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AppUser;
import com.example.demo.resourceassembler.AppUserResourceAssembler;
import com.example.demo.resources.AppUserResource;
import com.example.demo.services.AppUsersService;

@RestController
@RequestMapping("/users")
public class AppUsersRestController {

	@Autowired
	AppUsersService appUsersService;

	@GetMapping()
	public Resources<AppUserResource> getAppUsers() {

		PageRequest page = PageRequest.of(0, 2);
		List<AppUser> users = this.appUsersService.findAll(page).getContent();
		List<AppUserResource> userResource = new AppUserResourceAssembler().toResources(users);
		Resources<AppUserResource> allUsersResources = new Resources<AppUserResource>(userResource);

		allUsersResources.add(linkTo(methodOn(AppUsersRestController.class).getAppUsers()).withRel("allUsers"));

		return allUsersResources;
	}

	@GetMapping("/{id}")
	public Optional<AppUser> getOne(@PathVariable long id) {
		return this.appUsersService.findById(id);
	}

}
