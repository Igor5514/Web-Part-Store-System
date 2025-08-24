package com.example.server_354.object;

import com.example.server_354.security.UserDetailsImpl;

public class AuthResponse {
    private String token;
    private String email;
    private String fullName;
    private String role;
    private String phoneNumber;

    public AuthResponse(String token, UserDetailsImpl user) {
        this.token = token;
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.phoneNumber = user.getPhoneNumber();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
