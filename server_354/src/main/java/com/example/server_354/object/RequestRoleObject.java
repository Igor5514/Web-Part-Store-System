package com.example.server_354.object;

public class RequestRoleObject {

    private String role;
    private String email;
    private boolean isAccepted;

    public RequestRoleObject(String role, String email, boolean isAccepted) {
        this.role = role;
        this.email = email;
        this.isAccepted = isAccepted;
    }

    public RequestRoleObject() {
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
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
