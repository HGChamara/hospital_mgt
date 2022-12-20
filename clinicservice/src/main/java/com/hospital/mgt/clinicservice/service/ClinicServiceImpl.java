package com.hospital.mgt.clinicservice.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.hospital.mgt.clinicservice.entity.Clinic;
import com.hospital.mgt.clinicservice.entity.Doctor;
import com.hospital.mgt.clinicservice.model.AddClinicRequest;
import com.hospital.mgt.clinicservice.repository.ClinicRepository;
import com.hospital.mgt.clinicservice.repository.DoctorRepository;

@Service
public class ClinicServiceImpl implements ClinicService {

	private ClinicRepository clinicRepository;
	private DoctorService doctorService;
	
	public ClinicServiceImpl(ClinicRepository clinicRepository, DoctorService doctorService) {
		this.clinicRepository = clinicRepository;
		this.doctorService = doctorService;
	}
	
	
	@Override
	public List<Clinic> findAllClinics() {
		return clinicRepository.findAll();
	}

	@Override
	public Clinic findClinicById(Long id) {
		Optional<Clinic> clinicOpt = clinicRepository.findById(id);
		if(clinicOpt.isPresent()) {
			return clinicOpt.get(); 
		} else {
			throw new EntityNotFoundException("Clinic not found.");
		}
	}

	@Override
	public Clinic addClinic(AddClinicRequest clinicRequest) {
		
		//Doctor doctor = doctorService.findDoctorById(clinicRequest.getDoctorId());
		Clinic clinic = Clinic.builder()
				.name(clinicRequest.getName())
				//.doctor(doctor)
				.build();
		return clinicRepository.save(clinic);
	}


	@Override
	public void addDoctorToClinic(Long clinicId, Long doctorId) {
		Doctor doctor = doctorService.findDoctorById(clinicId);
		Clinic clinic = findClinicById(clinicId);
		
		clinic.setDoctor(doctor);
		clinicRepository.save(clinic);
	}

}
