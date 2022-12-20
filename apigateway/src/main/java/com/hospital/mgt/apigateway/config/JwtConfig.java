package com.hospital.mgt.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class JwtConfig {
	private String secret;
	private long validity;
	private boolean authDisabled;
}