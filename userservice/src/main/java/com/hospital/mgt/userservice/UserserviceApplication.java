package com.hospital.mgt.userservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospital.mgt.userservice.model.AppUserModel;
import com.hospital.mgt.userservice.service.AppUserServiceImpl;

@SpringBootApplication
public class UserserviceApplication {

	
	AppUserServiceImpl appUserService;
	
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	public void addUser() throws Exception {
		AppUserModel appUser = AppUserModel.builder()
				.username("user2")
				.password("password2")
				.role("admin").build();
		appUserService.signUpUser(appUser);
	}
	

}
