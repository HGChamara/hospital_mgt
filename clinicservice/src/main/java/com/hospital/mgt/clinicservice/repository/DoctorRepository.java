package com.hospital.mgt.clinicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.mgt.clinicservice.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
