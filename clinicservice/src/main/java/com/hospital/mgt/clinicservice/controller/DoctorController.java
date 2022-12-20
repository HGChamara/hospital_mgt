package com.hospital.mgt.clinicservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.mgt.clinicservice.entity.Doctor;
import com.hospital.mgt.clinicservice.service.DoctorService;

@RestController
@RequestMapping(value="/api/doctors")
public class DoctorController {
	
	DoctorService doctorService;
	
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<?> getAllDoctorDetails() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctorService.saveDoctor(doctor);
		return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
	}
}
