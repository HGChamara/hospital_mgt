package com.hospital.mgt.userservice.service;

import com.hospital.mgt.userservice.entity.AppUser;
import com.hospital.mgt.userservice.model.AppUserModel;

public interface AppUserService {
	
	AppUser signUpUser(AppUserModel appUserModel) throws Exception;
	AppUser getUserDetails(String username) throws Exception;
}