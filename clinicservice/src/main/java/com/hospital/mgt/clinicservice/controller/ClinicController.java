package com.hospital.mgt.clinicservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.mgt.clinicservice.entity.Clinic;
import com.hospital.mgt.clinicservice.model.AddClinicRequest;
import com.hospital.mgt.clinicservice.service.ClinicService;

@RestController
@RequestMapping(path="/api/clinics")
public class ClinicController {

	ClinicService clinicService;
	
	public ClinicController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllClinicDetails() {
		List<Clinic> clinics = clinicService.findAllClinics();
		return new ResponseEntity<>(clinics, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getClinicById(@PathVariable Long id) {
		Clinic clinic = clinicService.findClinicById(id);
		return new ResponseEntity<>(clinic, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addClinic(@RequestBody AddClinicRequest request) {
		Clinic savedClinic = clinicService.addClinic(request);
		return new ResponseEntity<>(savedClinic, HttpStatus.OK);
	}
	
	@PostMapping("/{clinicId}/{doctorId}")
	public ResponseEntity<?> addDoctorToClinic(@PathVariable Long clinicId, @PathVariable Long doctorId) {
		clinicService.addDoctorToClinic(clinicId, doctorId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
