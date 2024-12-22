package com.outpatient.service;

import com.outpatient.entity.DoctorAvailability;
import com.outpatient.repository.DoctorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAvailabilityService {
    @Autowired
    private DoctorAvailabilityRepository availabilityRepository;

    public DoctorAvailability addAvailability(DoctorAvailability availability) {
        return availabilityRepository.save(availability);
    }

    public List<DoctorAvailability> getAvailabilityByDoctorId(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }
    // New method to get availability by doctor's name
    public List<DoctorAvailability> getAvailabilityByDoctorName(String doctorName) {
        return availabilityRepository.findByDoctor_Name(doctorName);
    }
}