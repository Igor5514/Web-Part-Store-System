package com.example.server_354.Services;

import com.example.server_354.object.RequestedRoles;
import com.example.server_354.object.RoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleRequestRepository extends JpaRepository<RoleRequest, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET role = :role WHERE email = :email " ,nativeQuery = true)
    void updateRequestedRoleStatus(@Param("role") String role, @Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET role = 'user' WHERE email = :email " ,nativeQuery = true)
    void ReverseUpdateRequestedRoleStatus(@Param("email") String email);

    @Query(value = "SELECT rr.role_id, u.full_name, u.email, rr.role, rr.accept " +
            "FROM role_request rr " +
            "LEFT JOIN `user` u ON u.email = rr.email", nativeQuery = true)
    List<RequestedRoles> getRequestedRolesWithUserNames();
}
