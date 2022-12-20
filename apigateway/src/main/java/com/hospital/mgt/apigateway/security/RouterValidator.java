package com.hospital.mgt.apigateway.security;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {
	
	public static final List<String> nonSecureApiEndpoints = List.of(
			"/eureka",
			"/api-docs",
			"/api-gateway/authenticate",
			"/api-gateway/refreshToken"
	);
	
	public Predicate<ServerHttpRequest> isSecured = 
			request -> nonSecureApiEndpoints
						.stream()
						.noneMatch(uri -> request.getURI().getPath().contains(uri));
	
}