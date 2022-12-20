package com.hospital.mgt.apigateway.service;

import com.hospital.mgt.apigateway.model.AppUserModel;
import com.hospital.mgt.apigateway.security.AuthenticationStatus;

public interface JwtAuthenticationService {
	AuthenticationStatus authenticate(String username, String password);
	AppUserModel getUserByUsername(String username);
	AppUserModel getUserByToken(String token);
}
