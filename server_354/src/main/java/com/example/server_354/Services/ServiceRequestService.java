package com.example.server_354.Services;

import com.example.server_354.object.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        try {
            System.out.println(serviceRequest.getFullName());
            System.out.println(serviceRequest.getEmail());
            System.out.println(serviceRequest.getMechEmail());
            System.out.println(serviceRequest.getProblemType());
            System.out.println(serviceRequest.getProblemDescription());
            System.out.println(serviceRequest.getisDone());
            return serviceRequestRepository.save(serviceRequest);
        } catch (Exception e) {
            System.err.println("Error creating service request: " + e.getMessage());
            throw new RuntimeException("Error creating service request", e);
        }
    }

    public List<ServiceRequest> getAllServiceRequests() {
        try {
            return serviceRequestRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error fetching service requests: " + e.getMessage());
            throw new RuntimeException("Error fetching service requests", e);
        }
    }

    public List<ServiceRequest> getServiceRequestsByEmail(String email) {
        try {
            List<ServiceRequest> requests = serviceRequestRepository.findAllByMechEmail(email);
            if (requests.isEmpty()) {
                throw new RuntimeException("No service requests found for email: " + email);
            }
            return requests;
        } catch (Exception e) {
            System.err.println("Error fetching service requests for email: " + e.getMessage());
            throw new RuntimeException("Error fetching service requests for email: " + email, e);
        }
    }

    public void updateServiceRequestStatus(int serviceId) {
        try {
            serviceRequestRepository.updateIsDoneById(serviceId,true);
        } catch (Exception e) {
            System.err.println("Error updating service request status: " + e.getMessage());
            throw new RuntimeException("Error updating service request status", e);
        }
    }
}
