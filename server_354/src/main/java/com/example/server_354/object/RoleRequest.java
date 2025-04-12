package com.example.server_354.object;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId = 0;
    private String role;
    private String email;

    public RoleRequest(String role, String email) {
        this.role = role;
        this.email = email;
    }

    public RoleRequest() {
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
