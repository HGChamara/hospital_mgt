package com.hospital.mgt.clinicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.mgt.clinicservice.entity.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}