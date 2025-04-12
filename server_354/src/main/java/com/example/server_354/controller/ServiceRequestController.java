package com.example.server_354.controller;

import com.example.server_354.Services.ServiceRequestService;
import com.example.server_354.object.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceRequest createdServiceRequest = serviceRequestService.createServiceRequest(serviceRequest);
            return new ResponseEntity<>(createdServiceRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllServices")
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        try {

            List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();
            return new ResponseEntity<>(serviceRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getServiceByEmail")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByEmail(@RequestBody String email) {
        try {
            String request = email.substring(1, email.length() - 1);

            List<ServiceRequest> serviceRequests = serviceRequestService.getServiceRequestsByEmail(request);
            return new ResponseEntity<>(serviceRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateServiceStatus")
    public ResponseEntity<String> updateServiceRequestStatus(@RequestBody String serviceId) {
        try {
            System.out.println(serviceId);
            serviceRequestService.updateServiceRequestStatus(Integer.parseInt(serviceId));
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

