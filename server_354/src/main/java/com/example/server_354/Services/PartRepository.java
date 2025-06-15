package com.example.server_354.Services;

import com.example.server_354.object.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Parts, Long> {

}
