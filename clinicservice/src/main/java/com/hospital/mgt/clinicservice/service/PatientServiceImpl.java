package com.hospital.mgt.clinicservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.hospital.mgt.clinicservice.entity.Clinic;
import com.hospital.mgt.clinicservice.entity.Patient;
import com.hospital.mgt.clinicservice.model.AddPatientRequest;
import com.hospital.mgt.clinicservice.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	private ClinicService clinicService;
	
	public PatientServiceImpl(PatientRepository patientRepository, ClinicService clinicService) {
		this.patientRepository = patientRepository;
		this.clinicService = clinicService;
	}
	
	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient findPatientById(Long id) {
		
		Optional<Patient> patientOpt = patientRepository.findById(id);
		if(patientOpt.isPresent()) {
			return patientOpt.get();
		} else {
			throw new EntityNotFoundException("Requested patient not found.");
		}
	}

	@Override
	public Patient addPatient(AddPatientRequest request) {
		
		//Clinic clinic = clinicService.findClinicById(request.getClinicId());
		
		Patient patient = Patient.builder()
				.name(request.getName())
				.number(request.getNumber())
				//.clinics(List.of(clinic))
				.build();
		
		return patientRepository.save(patient);
		//TODO send welcome sms and email
	}

	@Override
	public void addPatientToClinic(Long patientId, Long clinicId) {
		Clinic clinic = clinicService.findClinicById(clinicId);
		Patient patient = findPatientById(patientId);
		patient.getClinics().add(clinic);
		patientRepository.save(patient);
		//send clinic sms and email
	}

}
