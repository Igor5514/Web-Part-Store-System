package com.example.server_354.Services;

import com.example.server_354.object.CarPartGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartGroupRepository extends JpaRepository<CarPartGroup,Long> {

    @Query(value = "SELECT part_type_name FROM car_part_type " +
            "WHERE group_id = :groupId " +
            "LIMIT 4", nativeQuery = true)
    List<String> getFourPartsByGroupName(@Param("groupId") int groupId);

    @Query(value = "SELECT group_id FROM car_part_group " +
            "WHERE group_name = :groupName", nativeQuery = true)
    int getGroupIdByGroupName(@Param("groupName") String groupName);


}
