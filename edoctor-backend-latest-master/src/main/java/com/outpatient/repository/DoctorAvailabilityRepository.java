package com.outpatient.repository;

import com.outpatient.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    List<DoctorAvailability> findByDoctor_Name(String doctorName);
    List<DoctorAvailability> findByDoctorId(Long doctorId);
}