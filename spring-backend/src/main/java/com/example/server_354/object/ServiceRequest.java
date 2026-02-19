package com.example.server_354.object;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
    private String fullName;
    private String email;
    private String mechEmail;
    private String problemType;
    private String problemDescription;
    private boolean isDone;

    public ServiceRequest(String fullName, String email,String mechEmail, String problemType, String problemDescription, boolean isDone) {


        this.fullName = fullName;
        this.email = email;
        this.mechEmail = mechEmail;
        this.problemType = problemType;
        this.problemDescription = problemDescription;
        this.isDone = isDone;
    }

    public ServiceRequest() {
    }



    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public String getMechEmail() {
        return mechEmail;
    }

    public void setMechEmail(String mechEmail) {
        this.mechEmail = mechEmail;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public boolean getisDone() {
        return isDone;
    }

    public void setisDone(boolean done) {
        isDone = done;
    }
}