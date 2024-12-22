package com.outpatient.dto;

import com.outpatient.entity.Appointment;

import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;
    private String patientUsername;
    private String doctorName;
    private LocalDateTime appointmentTime;

    // Constructor
    public AppointmentDto(Appointment appointment) {
        this.id = appointment.getId();
        this.patientUsername = appointment.getUser().getUsername(); // Extract patient username
        this.doctorName = appointment.getDoctor().getName(); // Extract doctor name
        this.appointmentTime = appointment.getAppointmentTime();
    }

    // Getters
    public Long getId() { return id; }
    public String getPatientUsername() { return patientUsername; }
    public String getDoctorName() { return doctorName; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
}