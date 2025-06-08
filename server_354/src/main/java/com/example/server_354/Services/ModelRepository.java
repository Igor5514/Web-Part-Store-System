package com.example.server_354.Services;

import com.example.server_354.object.Model;
import com.example.server_354.object.ModelObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query(value = "SELECT model from model where make_id = :makeId", nativeQuery = true)
    List<String> getModelByMakeId(@Param("makeId") int makeId);

    @Query(value = "SELECT model_id from model WHERE model = :model", nativeQuery = true)
    Integer getModelIdByModel(@Param("model") String model);

    @Query(value = "SELECT model_id, model FROM model", nativeQuery = true)
    List<ModelObject> getModelObject();

    @Query(value = "SELECT exists(select 1 from model where model.model = :model)", nativeQuery = true)
    Long checkIfModelExist(@Param("model") String model);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO model (make_id, model) values (:makeId :model)", nativeQuery = true)
    void addVehicleModel(@Param("makeId") Integer makeId, @Param("model") String model);




}