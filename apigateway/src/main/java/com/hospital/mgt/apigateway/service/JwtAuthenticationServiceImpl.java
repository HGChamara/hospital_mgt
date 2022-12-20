package com.hospital.mgt.apigateway.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hospital.mgt.apigateway.model.AppUserModel;
import com.hospital.mgt.apigateway.security.AuthenticationStatus;
import com.hospital.mgt.apigateway.security.JwtTokenUtil;

@Service
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {
	
	private final RestTemplate restTemplate;
	private final JwtTokenUtil jwtTokenUtil;
	
	public JwtAuthenticationServiceImpl(RestTemplate restTemplate, JwtTokenUtil jwtTokenUtil) {
		this.restTemplate = restTemplate;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	public AuthenticationStatus authenticate(String username, String password) {
		AuthenticationStatus status;
		try {
			AppUserModel userRegistered = getUserByUsername(username);
			if(username.equals(userRegistered.getUsername()) && password.equals(userRegistered.getPassword())) {
				status = new AuthenticationStatus(true, "Authentication successful.");
			}
			else {
				status = new AuthenticationStatus(false, "Authentication failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = new AuthenticationStatus(false, e.getLocalizedMessage());
		}
		return status;
	}

	@Override
	public AppUserModel getUserByUsername(String username) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		AppUserModel userRegistered = restTemplate.getForObject("http://localhost:8082/api/user/getUserByUsername/{username}", AppUserModel.class, params);
		return userRegistered;
	}

	@Override
	public AppUserModel getUserByToken(String refreshToken) {
		String username = jwtTokenUtil.getUsernameOfTheToken(refreshToken);
		AppUserModel user = getUserByUsername(username);
		return user;
		
	}
	
	

}
