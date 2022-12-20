package com.hospital.mgt.clinicservice.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.hospital.mgt.clinicservice.entity.Doctor;
import com.hospital.mgt.clinicservice.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

	private DoctorRepository doctorRepository;
	
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	
	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor findDoctorById(Long id) {
		Optional<Doctor> doctorOpt = doctorRepository.findById(id);
		if(doctorOpt.isPresent()) {
			return doctorOpt.get();
		} else  {
			throw new EntityNotFoundException("The requested doctor is not in the system.");
		}
	}

	@Override
	public Doctor saveDoctor(Doctor doctor) {
		Doctor savedDoctor = doctorRepository.save(doctor);
		return savedDoctor;
	}

}
