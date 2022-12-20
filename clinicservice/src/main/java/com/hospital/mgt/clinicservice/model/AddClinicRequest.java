package com.hospital.mgt.clinicservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddClinicRequest {
	private String name;
	private Long doctorId;
}
