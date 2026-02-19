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

    public List<String> getMechanicEmails(){
        return serviceRequestRepository.getMechanicEmails("mechanic");

    }

    public boolean checkForIsDoneStatus(int serviceId){
        return serviceRequestRepository.checkForIsDoneStatus(serviceId);
    }

    public void createServiceRequest(ServiceRequest serviceRequest) {
        try {
            serviceRequestRepository.save(serviceRequest);
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

    public List<ServiceRequest> getServiceRequestsByEmail(String email, String role) {
        try {
            List<ServiceRequest> requests;
            if(role.equalsIgnoreCase("mechanic")){
                requests =serviceRequestRepository.findAllByMechEmail(email);
            }else{
                requests = serviceRequestRepository.findAllByEmail(email);
            }
            if (email.isEmpty()) {
                throw new RuntimeException("No service requests found for email: " + email);
            }
            return requests;
        } catch (Exception e) {
            System.err.println("Error fetching service requests for email: " + e.getMessage());
            throw new RuntimeException("Error fetching service requests for email: " + email, e);
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

    public void updateServiceRequestStatus(int serviceId, boolean isDone) {
        try {
            serviceRequestRepository.updateIsDoneById(serviceId,isDone);
        } catch (Exception e) {
            throw new RuntimeException("Error updating service request status", e);
        }
    }
}
