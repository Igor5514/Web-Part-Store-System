package com.example.server_354.Services;

import com.example.server_354.object.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EngineRepository extends JpaRepository<Engine, Long> {

    @Query(value = """
    SELECT e.engine FROM engine e 
    JOIN model_generation_engine mge ON e.engine_id = mge.engine_id
    WHERE mge.model_id = :modelId AND mge.generation_id = :generationId
    """, nativeQuery = true)
    List<String> getEngineByModelAndGeneration(@Param("modelId") int modelId, @Param("generationId") int generationId);

    @Query(value = "SELECT exists(select 1 from engine where engine.engine = :engine)", nativeQuery = true)
    Long checkIfEngineExist(@Param("engine") String engine);

    @Query(value = "SELECT engine_id from engine WHERE engine = :engine", nativeQuery = true)
    Integer getEngineIdByEngine(@Param("engine") String engine);

    @Query(value = "SELECT COUNT(*) FROM model_generation_engine " +
            "WHERE model_id = :modelId AND generation_id = :generationId AND engine_id = :engineId", nativeQuery = true)
    Long checkIfModelGenerationEngineTableKeyExists(@Param("modelId") int modelId, @Param("generationId") int generationId, @Param("engineId") int engineId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO engine (engine) values (:engine)", nativeQuery = true)
    void addVehicleEngine(@Param("engine") String engine);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO model_generation_engine (model_id, generation_id, engine_id) values (:modelId, :generationId , :engineId)", nativeQuery = true)
    void addVehicleModelGenerationEngine(@Param("modelId") int modelId, @Param("generationId") int generationId, @Param("engineId") int engineId);

}