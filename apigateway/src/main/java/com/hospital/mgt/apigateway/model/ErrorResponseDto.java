package com.hospital.mgt.apigateway.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
	private Date date;
	private int status;
	private String error;
	private List<String> details;
	private String path;
}
