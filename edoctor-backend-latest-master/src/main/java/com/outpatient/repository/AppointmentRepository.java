package com.outpatient.repository;

import com.outpatient.entity.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @EntityGraph(attributePaths = {"user", "doctor"})
    List<Appointment> findByDoctor_Name(String doctorName);

    List<Appointment> findByDoctor_Username(String doctorUsername);

    List<Appointment> findByUser_Username(String username);
}