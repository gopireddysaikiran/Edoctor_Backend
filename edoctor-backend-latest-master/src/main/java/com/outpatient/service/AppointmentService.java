package com.outpatient.service;

import com.outpatient.entity.*;
import com.outpatient.dto.AppointmentRequest;
import com.outpatient.repository.AppointmentRepository;
import com.outpatient.repository.DoctorRepository;
import com.outpatient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public Appointment scheduleAppointment(AppointmentRequest appointmentRequest) {
        String doctorName = appointmentRequest.getDoctorName();
        String username = appointmentRequest.getUsername();
        LocalDateTime appointmentTime = LocalDateTime.parse(appointmentRequest.getAppointmentTime());

        Doctor doctor = doctorRepository.findByName(doctorName)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorName));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // Check for conflicts
        List<Appointment> conflictingAppointments = appointmentRepository.findAll();
        for (Appointment a : conflictingAppointments) {
            if (a.getDoctor().getId().equals(doctor.getId()) && a.getAppointmentTime().equals(appointmentTime)) {
                throw new RuntimeException("The doctor is already booked for this time slot.");
            }
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setUser(user);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setReason(appointmentRequest.getReason()); // Set the reason
        appointment.setPaymentStatus(Appointment.PaymentStatus.PENDING); // Set initial payment status

        // Here you would implement the payment logic
        boolean paymentSuccessful = processPayment(user.getUsername(), appointmentRequest.getAmount());
        if (!paymentSuccessful) {
            throw new RuntimeException("Payment failed");
        }

        appointment.setPaymentStatus(Appointment.PaymentStatus.PAID); // Mark as paid if payment succeeded

        Appointment savedAppointment = appointmentRepository.save(appointment);
        emailService.sendAppointmentNotification(savedAppointment);

        return savedAppointment;
    }

    public List<Appointment> getAppointmentsByDoctorUsername(String doctorUsername) {
        List<Appointment> appointments = appointmentRepository.findByDoctor_Username(doctorUsername);
        if (appointments.isEmpty()) {
            logger.info("No appointments found for doctor with username: {}", doctorUsername);
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByUsername(String username) {
        List<Appointment> appointments = appointmentRepository.findByUser_Username(username);
        if (appointments.isEmpty()) {
            logger.info("No appointments found for user with username: {}", username);
        }
        return appointments;
    }

    public Appointment confirmAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(Appointment.AppointmentStatus.CONFIRMED);
        return appointmentRepository.save(appointment);
    }

    public Appointment rejectAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(Appointment.AppointmentStatus.REJECTED);
        return appointmentRepository.save(appointment);
    }

    private boolean processPayment(String username, double amount) {
        // Implement payment gateway integration here
        return true; // Simulating a successful payment
    }
    public String getAppointmentStatus(String username) {
        List<Appointment> appointments = appointmentRepository.findByUser_Username(username);
        if (appointments.isEmpty()) {
            return "You don't have any appointments.";
        }
        return "Your latest appointment is on: " + appointments.get(0).getAppointmentTime() + " with status: " + appointments.get(0).getStatus();
    }

}