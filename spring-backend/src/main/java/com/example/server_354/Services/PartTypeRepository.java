package com.example.server_354.Services;

import com.example.server_354.object.CarPartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartTypeRepository extends JpaRepository<CarPartType, Long> {

    @Query(value = "SELECT * FROM car_part_type " +
            "WHERE group_id = :groupId", nativeQuery = true)
    List<CarPartType> getPartsTypeByGroupName(@Param("groupId") int groupId);


}
