package com.example.server_354.Services;

import com.example.server_354.object.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PartRepository extends JpaRepository<Parts, Long> {


    @Query(value = "SELECT p.part_id, p.name, p.part_number, p.brand, p.price, " +
            "p.stock_quantity, p.image, p.description, p.weight, " +
            "p.dimensions, p.vehicle_type, p.date_added, p.last_updated " +
            "FROM parts p " +
            "INNER JOIN part_category pc ON p.part_id = pc.part_id " +
            "WHERE pc.model_id = :modelId " +
            "AND pc.generation_id = :generationId " +
            "AND pc.engine_id = :engineId " +
            "AND p.name = :name", nativeQuery = true)
    List<Parts> getPartsForSpecificVehicle(@Param("modelId") Integer modelId,
                                           @Param("generationId") Integer generationId,
                                           @Param("engineId") Integer engineId,
                                           @Param("name") String name);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart (user_id, part_id) values(:userId, :partId)", nativeQuery = true)
    void postCartItem(@Param("partId") Integer partId,@Param("userId") Integer userId);

    @Query(value = "SELECT p.* FROM parts p " +
            "INNER JOIN cart c ON c.part_id = p.part_id " +
            "WHERE c.user_id = :user_id", nativeQuery = true)
    List<Parts> getCartItemsByUserId(@Param("user_id") Integer userId);
}
