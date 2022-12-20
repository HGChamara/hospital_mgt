package com.hospital.mgt.clinicservice.service;

import java.util.List;

import com.hospital.mgt.clinicservice.entity.Patient;
import com.hospital.mgt.clinicservice.model.AddPatientRequest;

public interface PatientService {
	List<Patient> findAllPatients();
	Patient findPatientById(Long id);
	Patient addPatient(AddPatientRequest request);
	void addPatientToClinic(Long patientId, Long clinicId);
}
