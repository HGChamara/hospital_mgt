package com.hospital.mgt.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserModel {
	
	private int id;
	private String username;
	private String password;
	private String role;

}