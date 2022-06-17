package com.hospital.mgt.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.hospital.mgt.userservice.entity.AppUser;
import com.hospital.mgt.userservice.model.AppUserModel;
import com.hospital.mgt.userservice.repository.AppUserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
//TO-DO try to user custom configuration class @configurationadasdas

public class AppUserServiceTest {
	@Autowired
	private AppUserRepository repository;
	
	@Autowired
	private AppUserService service;
	
	@Test
	void injectedComponentsAreNotNull() {
		assertNotNull(repository);
	}
	
	@Test
	@DisplayName("SHOULD successfully insert data WHEN a valid appUserModel inputted.")
	void successfullyInsertData() throws Exception {
		AppUserModel appUserModel = AppUserModel.builder().id(123).username("user1").password("pass1").role("admin").build();
		service.signUpUser(appUserModel);
		Optional<AppUser> userOp = repository.findByUsername(appUserModel.getUsername());
		assertEquals(userOp.get().getUsername(), appUserModel.getUsername());
		
	}
	
	
	

}
