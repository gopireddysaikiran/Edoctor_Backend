package com.outpatient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequest {
    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Appointment time is required")
    private String appointmentTime;

    @NotBlank(message = "Consultation type is required")
    private String consultationType;

    @NotBlank(message = "Reason is required")
    private String reason;

    private String paymentId;

    private double amount; // Amount to be paid

    // Getters and Setters
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getConsultationType() { return consultationType; }
    public void setConsultationType(String consultationType) { this.consultationType = consultationType; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}