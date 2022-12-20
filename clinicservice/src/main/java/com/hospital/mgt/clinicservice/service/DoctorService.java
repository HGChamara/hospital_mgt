package com.hospital.mgt.clinicservice.service;

import java.util.List;

import com.hospital.mgt.clinicservice.entity.Doctor;

public interface DoctorService {
	
	List<Doctor> getAllDoctors();
	Doctor findDoctorById(Long id);
	Doctor saveDoctor(Doctor doctor);
}
