//// src/main/java/com/yourpackage/controller/ChatbotController.java
//package com.outpatient.controller;
//
//import com.outpatient.dto.ChatbotRequest;
//import com.outpatient.dto.ChatbotResponse;
//import com.outpatient.entity.Doctor;
//import com.outpatient.repository.AppointmentRepository;
//import com.outpatient.repository.DoctorRepository;
//import com.outpatient.service.AppointmentService;
//import com.outpatient.service.DoctorService;
//import com.outpatient.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.outpatient.service.ChatbotService; // Create a ChatbotService to handle business logic
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/chatbot")
//public class ChatbotController {
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @Autowired
//    private AppointmentRepository appointmentRepository;
//    @PostMapping("/respond")
//    public ResponseEntity<Map<String, String>> respondToQuery(@RequestBody Map<String, String> request) {
//        String query = request.get("query").toLowerCase();
//        String response;
//
//        if (query.contains("doctor availability") || query.contains("available doctors today")) {
//            response = getDoctorAvailability();
//        } else if (query.contains("appointment status")) {
//            response = "Your appointment is scheduled for tomorrow.";
//        } else {
//            response = "I'm sorry, I don't understand the question.";
//        }
//
//        Map<String, String> reply = new HashMap<>();
//        reply.put("reply", response);
//        return ResponseEntity.ok(reply);
//    }
//
//    private String getDoctorAvailability() {
//        // Logic to fetch available doctors from your database
//        List<Doctor> doctors = doctorRepository.findAll();
//        if (doctors.isEmpty()) {
//            return "No doctors are available today.";
//        }
//        StringBuilder response = new StringBuilder("Available doctors today: ");
//        for (Doctor doctor : doctors) {
//            response.append(doctor.getName()).append(" (").append(doctor.getSpecialization()).append("), ");
//        }
//        return response.toString();
//    }
//
//}


package com.outpatient.controller;

import com.outpatient.entity.Doctor;
import com.outpatient.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping("/respond")
    public ResponseEntity<Map<String, String>> respondToQuery(@RequestBody Map<String, String> request) {
        String query = request.get("query").toLowerCase();  // Make query case-insensitive
        String response;

        // Answer for "What are the operating hours for doctors?"
        if (query.contains("operating hours for doctors") || query.contains("what are the operating hours")) {
            response = "Doctors are available from 9 AM to 5 PM, Monday to Friday.";
        }
        // For "How can I schedule an appointment?"
       else if (query.contains("schedule an appointment") || query.contains("book an appointment") || query.contains("how can I schedule")) {
            response = "You can schedule an appointment through the 'Appointments' section on the website or mobile app. Select a doctor and choose an available time slot.";
        }

        // For "Can you tell me the status of my appointment?"
        else if (query.contains("appointment status") || query.contains("status of my appointment") || query.contains("when is my appointment")) {
            response = "To check the status of your appointment, please visit the 'Appointments' page in your account.";
        }

        // For "Which doctors are available this week?"
        else if (query.contains("doctors available this week") || query.contains("available doctors this week")) {
            response = getDoctorAvailabilityThisWeek();
        }

        // For "How do I reset my password?"
        else if (query.contains("reset my password") || query.contains("how to reset my password") || query.contains("forgot password")) {
            response = "To reset your password, click on 'Forgot Password' on the login page, and follow the instructions sent to your email.";
        }

        // For "What are the available doctors today?"
        else if (query.contains("available doctors today") || query.contains("doctors available today")) {
            response = getDoctorAvailabilityToday();
        }

        // Default response for unrecognized questions
        else {
            response = "I'm sorry, I don't understand the question.";
        }

        Map<String, String> reply = new HashMap<>();
        reply.put("reply", response);
        return ResponseEntity.ok(reply);
    }

    // Logic for fetching available doctors today
    private String getDoctorAvailabilityToday() {
        // You can customize this based on actual availability logic
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            return "No doctors are available today.";
        }
        StringBuilder response = new StringBuilder("Available doctors today: ");
        for (Doctor doctor : doctors) {
            response.append(doctor.getName()).append(" (").append(doctor.getSpecialization()).append("), ");
        }
        return response.toString();
    }

    // Logic for fetching doctor availability for the week
    private String getDoctorAvailabilityThisWeek() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            return "No doctors are available this week.";
        }
        StringBuilder response = new StringBuilder("Doctors available this week: ");
        for (Doctor doctor : doctors) {
            response.append(doctor.getName()).append(" (").append(doctor.getSpecialization()).append("), ");
        }
        return response.toString();
    }

}

