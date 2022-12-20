package com.hospital.mgt.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPatientRequest {
	private String name;
	private int number;
	private Long clinicId;
}
