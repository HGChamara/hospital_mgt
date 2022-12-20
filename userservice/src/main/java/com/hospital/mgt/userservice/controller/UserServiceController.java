package com.hospital.mgt.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.mgt.userservice.entity.AppUser;
import com.hospital.mgt.userservice.model.AppUserModel;
import com.hospital.mgt.userservice.service.AppUserService;

@RestController
@RequestMapping("/api/user/")
public class UserServiceController {
	
	private final AppUserService appUserService;
	
	public UserServiceController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@GetMapping(value = "/getUserByUsername/{username}")
	public ResponseEntity<?> getUserDetailsByUsername(@PathVariable String username) throws Exception {
		AppUser user = appUserService.getUserDetails(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<?> signUpUser(@RequestBody AppUserModel userModel) throws Exception {
		AppUser user = appUserService.signUpUser(userModel);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	
}
