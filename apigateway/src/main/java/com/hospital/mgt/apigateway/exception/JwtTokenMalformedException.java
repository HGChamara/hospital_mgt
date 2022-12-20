package com.hospital.mgt.apigateway.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenMalformedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JwtTokenMalformedException(String message) {
		super(message);
		log.error(this.getClass().getName()+" : "+message);
	}

}
