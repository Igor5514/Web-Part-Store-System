package com.example.server_354.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.server_354.object.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value = "SELECT EXISTS(SELECT 1 FROM user WHERE email = :email)", nativeQuery = true)
    Long getResponseByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);

    @Query(value = "SELECT id FROM user WHERE email = :email", nativeQuery = true)
    Integer getUserIdByEmail(@Param("email") String email);
}
