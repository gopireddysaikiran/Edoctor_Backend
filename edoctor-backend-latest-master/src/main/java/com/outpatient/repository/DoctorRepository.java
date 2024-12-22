package com.outpatient.repository;

import com.outpatient.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByName(String name); // Method to find doctor by name
    Optional<Doctor> findByUsername(String username);
    List<Doctor> findAll();

}