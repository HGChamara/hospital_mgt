package com.hospital.mgt.apigateway.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationStatus {
	private Boolean isAuthenticated;
	private String message;
}
