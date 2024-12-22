package com.outpatient.service;

import com.outpatient.entity.Doctor;
import com.outpatient.entity.User;
import com.outpatient.repository.DoctorRepository;
import com.outpatient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Generate a 4-digit OTP
    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 1000 + random.nextInt(9000); // Generates a number between 1000 and 9999
        return String.valueOf(otp);
    }

    // Generate an OTP and send it via email
    public void forgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with this email does not exist.");
        }

        User user = userOptional.get();

        // Generate OTP and set expiration time
        String otp = generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes

        userRepository.save(user); // Save user with OTP

        // Send the OTP email
        emailService.sendOtpEmail(email, user.getUsername(), otp); // Make sure EmailService matches this signature
    }

    // Verify OTP and mark user as verified
    public boolean verifyOtp(String username, String otp) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            System.out.println("User not found for username: " + username);
            return false; // User does not exist
        }

        User user = userOptional.get();

        // Verify OTP and expiration
        if (user.getOtp() != null && user.getOtp().trim().equals(otp.trim()) &&
                user.getOtpExpiration() != null && user.getOtpExpiration().isAfter(LocalDateTime.now())) {
            user.setVerified(true); // Mark user as verified
            user.setOtp(null); // Clear OTP
            user.setOtpExpiration(null); // Clear expiration
            userRepository.save(user); // Update user
            return true; // Successful verification
        }

        System.out.println("OTP verification failed for user: " + username);
        return false; // Invalid OTP or expired
    }

    public void resetPassword(String username, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword)); // Encode new password
            user.setOtp(null); // Clear OTP
            user.setOtpExpiration(null); // Clear expiration
            userRepository.save(user); // Update user
        }
    }

    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null); // Return the User object or null if not found
    }
    // Register a new user
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false); // Initially not verified

        String otp = generateOtp(); // Generate OTP
        user.setOtp(otp); // Store OTP
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(5)); // Set expiration for 5 minutes
        userRepository.save(user); // Save user with OTP

        // If the user is a doctor, also save the doctor information
        if ("DOCTOR".equalsIgnoreCase(user.getRole())) {
            Doctor doctor = new Doctor();
            doctor.setUsername(user.getUsername()); // Use the same username as the User entity
            doctor.setName(user.getUsername()); // Optionally set name to the username
            doctor.setSpecialization("General Practitioner"); // Example specialization
            doctor.setAvailable(true); // Set the doctor as available
            doctorRepository.save(doctor); // Save the Doctor entity
        }

        emailService.sendOtpEmail(user.getEmail(), user.getUsername(), otp); // Send OTP email
        return user;
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User userDetails) {
        User user = findById(id);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            // Set any other relevant fields
            return userRepository.save(user);
        }
        return null; // Or throw an exception
    }
}