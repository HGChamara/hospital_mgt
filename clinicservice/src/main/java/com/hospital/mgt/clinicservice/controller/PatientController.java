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

import com.hospital.mgt.clinicservice.entity.Patient;
import com.hospital.mgt.clinicservice.model.AddPatientRequest;
import com.hospital.mgt.clinicservice.service.PatientService;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientController {
	
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	public ResponseEntity<?> findAllPatients() {
		List<Patient> patients = patientService.findAllPatients();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addPatient(@RequestBody AddPatientRequest request) {
		Patient addedPatient = patientService.addPatient(request);
		return new ResponseEntity<>(addedPatient, HttpStatus.CREATED);
	}
	
	@PostMapping("/{patientId}/{clinicId}")
	public ResponseEntity<?> addPatientToClinic(@PathVariable("patientId") Long patientId, @PathVariable("clinicId") Long clinicId) {
		patientService.addPatientToClinic(patientId, clinicId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
