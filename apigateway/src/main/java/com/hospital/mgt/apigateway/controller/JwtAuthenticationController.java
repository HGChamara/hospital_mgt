package com.hospital.mgt.apigateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.hospital.mgt.apigateway.config.JwtConfig;
import com.hospital.mgt.apigateway.model.AppUserModel;
import com.hospital.mgt.apigateway.model.AuthenticationRequest;
import com.hospital.mgt.apigateway.model.AuthenticationResponse;
import com.hospital.mgt.apigateway.model.ErrorResponseDto;
import com.hospital.mgt.apigateway.security.AuthenticationStatus;
import com.hospital.mgt.apigateway.security.JwtTokenUtil;
import com.hospital.mgt.apigateway.service.JwtAuthenticationService;

import io.jsonwebtoken.Jwts;

import static com.hospital.mgt.apigateway.constants.Constants.API_GATEWAY_PREDICATE;

@RestController
public class JwtAuthenticationController {
	
	private final JwtConfig jwtConfig;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtAuthenticationService jwtAuthenticationService;
	
	
	public JwtAuthenticationController(JwtConfig jwtConfig, JwtTokenUtil jwtTokenUtil, JwtAuthenticationService jwtAuthenticationService) {
		this.jwtConfig = jwtConfig;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtAuthenticationService = jwtAuthenticationService;
	}
	
	@PostMapping(value = API_GATEWAY_PREDICATE+"/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		
		AuthenticationStatus status = jwtAuthenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		if(!status.getIsAuthenticated()) {
			List<String> details = new ArrayList<>();
			details.add(status.getMessage());
			ErrorResponseDto response = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "url");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		final String accessToken = jwtTokenUtil.generateAccessToken(authenticationRequest.getUsername());
		final String refreshToken = jwtTokenUtil.generateRefreshToken(authenticationRequest.getUsername());
		
		AuthenticationResponse response = AuthenticationResponse.builder().jwtAccessToken(accessToken).jwtRefreshToken(refreshToken).build();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = API_GATEWAY_PREDICATE+"/refreshToken")
	public ResponseEntity<?> refreshAccessToken(@RequestHeader("Authorization") String authHeader) {
		
		if(authHeader != null) {
			jwtTokenUtil.validateToken(authHeader);
			String refreshToken = Objects.requireNonNull(authHeader.substring("Bearer ".length()));
			AppUserModel user = jwtAuthenticationService.getUserByToken(refreshToken);
			final String newAccessToken = jwtTokenUtil.generateAccessToken(user.getUsername());
			final String newRrefreshToken = jwtTokenUtil.generateRefreshToken(user.getUsername());
			
			AuthenticationResponse response = AuthenticationResponse.builder().jwtAccessToken(newAccessToken).jwtRefreshToken(newRrefreshToken).build();
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.badRequest().body("PLEASE ENTER REFRESH TOKEN.");
		}
		
	}
	

}
