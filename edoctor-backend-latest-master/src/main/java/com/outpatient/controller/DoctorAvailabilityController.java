package com.outpatient.controller;

import com.outpatient.dto.DoctorAvailabilityRequest;
import com.outpatient.entity.Doctor;
import com.outpatient.entity.DoctorAvailability;
import com.outpatient.service.DoctorAvailabilityService;
import com.outpatient.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/availability")
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService availabilityService;

    @Autowired
    private DoctorService doctorService; // Service to retrieve Doctor by username or ID

    @PostMapping
    public ResponseEntity<?> addAvailability(@RequestBody DoctorAvailabilityRequest request) {
        // Find the doctor by username
        Doctor doctor = doctorService.findByUsername(request.getUsername());
        if (doctor == null) {
            return new ResponseEntity<>("Doctor not found.", HttpStatus.NOT_FOUND);
        }

        // Create a new DoctorAvailability object
        DoctorAvailability availability = new DoctorAvailability();
        availability.setDoctor(doctor); // Associate with the doctor
        availability.setDay(DoctorAvailability.Day.valueOf(request.getDay())); // Convert string to Enum
        availability.setStartTime(java.time.LocalTime.parse(request.getStartTime())); // Convert string to LocalTime
        availability.setEndTime(java.time.LocalTime.parse(request.getEndTime())); // Convert string to LocalTime

        // Save the availability
        DoctorAvailability savedAvailability = availabilityService.addAvailability(availability);
        return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
    }
}