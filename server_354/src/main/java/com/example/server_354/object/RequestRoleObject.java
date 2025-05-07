package com.example.server_354.object;

public class RequestRoleObject {

    private String role;
    private String email;

    public RequestRoleObject(String role, String email) {
        this.role = role;
        this.email = email;
    }

    public RequestRoleObject() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
