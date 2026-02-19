package com.example.server_354.object;

public class RequestRoleObject {

    private String role;
    private String email;
    private boolean accepted;

    public RequestRoleObject(String role, String email, boolean accepted) {
        this.role = role;
        this.email = email;
        this.accepted = accepted;
    }

    public RequestRoleObject() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
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
