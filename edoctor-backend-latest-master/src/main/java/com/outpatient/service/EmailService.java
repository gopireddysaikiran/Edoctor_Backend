package com.outpatient.service;

import com.outpatient.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        String resetLink = "http://localhost:3000/reset-password/" + resetToken; // Frontend link

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the following link:\n" + resetLink);

        mailSender.send(message);
    }
    public void sendOtpEmail(String to, String username, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Hi " + username + ",\n\nYour OTP code is: " + otp + "\n\nPlease enter this code to verify your email.");

        mailSender.send(message);
    }
    public void sendAppointmentNotification(Appointment appointment) {
        String subject = "Appointment Scheduled";
        String message = String.format(
                "Dear %s,\nYour appointment with Dr. %s is scheduled for %s.",
                appointment.getUser().getUsername(),
                appointment.getDoctor().getName(),
                appointment.getAppointmentTime()
        );

        System.out.println("Sending email to " + appointment.getUser().getEmail() + ": " + message);
    }
}