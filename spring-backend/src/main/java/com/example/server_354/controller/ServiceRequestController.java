package com.example.server_354.controller;

import com.example.server_354.Services.ServiceRequestService;
import com.example.server_354.object.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/services")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping("/createService")
    public ResponseEntity<String> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        try {
            serviceRequestService.createServiceRequest(serviceRequest);
            return ResponseEntity.ok("request created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error: " + e.getMessage());
        }
    }

    @GetMapping("/getMechEmails")
    public ResponseEntity<?> getMechanicEmails() {
        try {
            List<String> mechanicEmails = serviceRequestService.getMechanicEmails();
            return ResponseEntity.ok(mechanicEmails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error: " + e.getMessage());
        }
    }

    @PostMapping("/getServiceByEmail")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByEmail(@RequestBody String emailRole) {
        try {
            String request = emailRole.substring(1, emailRole.length() - 1);
            String email = (request.split(" "))[0];
            String role = (request.split(" "))[1];
            if(role.equalsIgnoreCase("admin")){
                List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();
                return new ResponseEntity<>(serviceRequests, HttpStatus.OK);
            }else{
                List<ServiceRequest> serviceRequests = serviceRequestService.getServiceRequestsByEmail(email, role);
                return new ResponseEntity<>(serviceRequests, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateServiceStatus")
    public ResponseEntity<String> updateServiceRequestStatus(@RequestBody String serviceId) {
        try {
            int id = Integer.parseInt(serviceId);
            if(serviceRequestService.checkForIsDoneStatus(id)){
                serviceRequestService.updateServiceRequestStatus(id,false);
            }else{
                serviceRequestService.updateServiceRequestStatus(id,true);
            }
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

