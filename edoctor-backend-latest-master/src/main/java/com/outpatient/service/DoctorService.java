package com.outpatient.service;

import com.outpatient.entity.Doctor;
import com.outpatient.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public Doctor findByUsername(String username) {
        return doctorRepository.findByName(username).stream().findFirst().orElse(null);
    }

    public String getDoctorAvailability() {
        List<Doctor> doctors = doctorRepository.findAll();
        StringBuilder response = new StringBuilder("Available Doctors: ");
        for (Doctor doctor : doctors) {
            response.append(doctor.getName()).append(" - ");
            doctor.getAppointments().forEach(appointment -> {
                response.append("Available on ").append(appointment.getAppointmentTime()).append(", ");
            });
        }
        return response.toString();
    }
}