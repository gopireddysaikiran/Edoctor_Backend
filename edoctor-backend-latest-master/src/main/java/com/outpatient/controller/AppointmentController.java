package com.outpatient.controller;

import com.outpatient.dto.AppointmentRequest;
import com.outpatient.entity.Appointment;
import com.outpatient.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.outpatient.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PaymentService paymentService;
    
//    @PostMapping("/schedule")
//    public ResponseEntity<Appointment> scheduleAppointment(@Validated @RequestBody AppointmentRequest request) {
//        Appointment appointment = appointmentService.scheduleAppointment(request);
//        return ResponseEntity.ok(appointment);
//    }
@PostMapping("/schedule")
public ResponseEntity<?> scheduleAppointment(@RequestBody AppointmentRequest request) {
    boolean isPaymentVerified = paymentService.verifyPayment(request.getPaymentId());

    if (!isPaymentVerified) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Payment verification failed.");
    }

    Appointment appointment = appointmentService.scheduleAppointment(request);
    return ResponseEntity.ok(appointment);
}


    @GetMapping("/doctor/{doctorUsername}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable String doctorUsername) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorUsername(doctorUsername);
        return ResponseEntity.ok(appointments);
    }

    // Get appointments by user's username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUsername(@PathVariable String username) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUsername(username);
        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Appointment> confirmAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.confirmAppointment(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<Appointment> rejectAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.rejectAppointment(id);
        return ResponseEntity.ok(appointment);
    }
}


