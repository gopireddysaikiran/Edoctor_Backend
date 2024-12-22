package com.outpatient.dto;

public class DoctorAvailabilityRequest {
    private String username; // Username of the doctor
    private String day; // Enum value (e.g., MONDAY)
    private String startTime; // In HH:mm format
    private String endTime; // In HH:mm format

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}