package com.hospital.mgt.clinicservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.mgt.clinicservice.entity.Clinic;
import com.hospital.mgt.clinicservice.entity.Doctor;
import com.hospital.mgt.clinicservice.entity.Patient;
import com.hospital.mgt.clinicservice.repository.ClinicRepository;
import com.hospital.mgt.clinicservice.repository.DoctorRepository;
import com.hospital.mgt.clinicservice.repository.PatientRepository;

@SpringBootApplication
public class ClinicserviceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ClinicserviceApplication.class, args);
	}
	
	
	
}
