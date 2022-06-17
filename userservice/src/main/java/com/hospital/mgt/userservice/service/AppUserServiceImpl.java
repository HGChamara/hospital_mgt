package com.hospital.mgt.userservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.mgt.userservice.entity.AppUser;
import com.hospital.mgt.userservice.model.AppUserModel;
import com.hospital.mgt.userservice.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	AppUserRepository appUserRepository;
	
	public AppUserServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}
	
	@Override
	public AppUser signUpUser(AppUserModel appUserModel) throws Exception {
		
		Optional<AppUser> userOp = appUserRepository.findByUsername(appUserModel.getUsername());
		if (userOp.isPresent()) {
			throw new Exception("User already exsists");
		}
		
		AppUser appUser = AppUser.builder()
			.id(appUserModel.getId())
			.password(appUserModel.getPassword())
			.username(appUserModel.getUsername())
			.role(appUserModel.getRole())
			.build();
		
		return appUserRepository.save(appUser);
	}

	@Override
	public AppUser getUserDetails(AppUserModel appUserModel) throws Exception {
		Optional<AppUser> userOp = appUserRepository.findByUsername(appUserModel.getUsername());
		
		if(userOp.isPresent()) {
			return userOp.get();
		}
		else {
			throw new Exception("Username not found.");
		}
	}
	
}
