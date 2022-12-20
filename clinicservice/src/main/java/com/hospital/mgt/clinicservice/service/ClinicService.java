package com.hospital.mgt.clinicservice.service;

import java.util.List;

import com.hospital.mgt.clinicservice.entity.Clinic;
import com.hospital.mgt.clinicservice.model.AddClinicRequest;

public interface ClinicService {
	List<Clinic> findAllClinics();
	Clinic findClinicById(Long id);
	Clinic addClinic(AddClinicRequest clinic);
	void addDoctorToClinic(Long clinicId, Long doctorId);

}
