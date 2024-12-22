package com.outpatient.controller;

import com.outpatient.dto.LoginRequest;
import com.outpatient.dto.LoginResponse;
import com.outpatient.entity.User;
import com.outpatient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok("OTP has been sent to your email.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Reset Password Endpoint
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String resetToken,
                                                @RequestParam String newPassword) {
        try {
            userService.resetPassword(resetToken, newPassword);
            return ResponseEntity.ok("Password has been reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Ensure role is set (default to PATIENT if not provided)
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("PATIENT");  // Default role
        }
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());

        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        if (!user.isVerified()) {
            return new ResponseEntity<>("User is not verified. Please verify your OTP.", HttpStatus.FORBIDDEN);
        }

        // Build JSON response with user details
        return ResponseEntity.ok().body(new LoginResponse(user.getUsername(), user.getRole()));
    }
    @PostMapping("/verify-otp/{username}")
    public ResponseEntity<String> verifyOtp(@PathVariable String username, @RequestBody String body) {
        // Extract the OTP from the JSON body
        String otp = extractOtpFromJson(body); // Call method to extract OTP
        boolean isVerified = userService.verifyOtp(username, otp);

        if (isVerified) {
            return new ResponseEntity<>("User verified successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP or OTP expired.", HttpStatus.BAD_REQUEST);
        }
    }

    private String extractOtpFromJson(String body) {
        // Assuming body is like {"otp": "6067"}
        // Use simple string manipulation to extract the OTP
        String otpString = body.replaceAll("[{}\"otp: ]", ""); // Remove unwanted characters
        return otpString; // Returns the OTP as a string
    }


    // Endpoint to update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/username/{username}") // Updated path
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Fetch user details by ID
    @GetMapping("/{id}") // This remains for ID-based fetching
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}