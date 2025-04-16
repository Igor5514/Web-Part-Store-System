package com.example.server_354.object;

public class RequestedRoles {
    private int roleId;
    private String fullName;
    private String email;
    private String role;

    public RequestedRoles(int roleId, String fullName, String email, String role) {
        this.roleId = roleId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public RequestedRoles() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
