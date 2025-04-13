package com.example.server_354.Services;

import com.example.server_354.object.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

    List<ServiceRequest> findAll();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM service_request WHERE mech_email = :mechEmail", nativeQuery = true)
    List<ServiceRequest> findAllByMechEmail(@Param("mechEmail") String mechEmail);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM service_request WHERE email = :email", nativeQuery = true)
    List<ServiceRequest> findAllByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM service_request WHERE email = :email", nativeQuery = true)
    List<ServiceRequest> findAllByNonMechEmail(@Param("email") String email);

    Optional<ServiceRequest> findById(Integer serviceId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE service_request SET is_done = :isDone WHERE service_id = :serviceId", nativeQuery = true)
    int updateIsDoneById(@Param("serviceId") int serviceId, @Param("isDone") boolean isDone);

    @Query(value = "SELECT is_done FROM service_request WHERE service_id = :serviceId", nativeQuery = true)
    boolean checkForIsDoneStatus(@Param("serviceId") int serviceId);

}
