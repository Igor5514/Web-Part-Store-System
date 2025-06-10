package com.example.server_354.Services;

import com.example.server_354.object.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EngineRepository extends JpaRepository<Engine, Long> {

    @Query(value = "SELECT engine from engine\n" +
                   "INNER join generation_engine ON engine.engine_id = generation_engine.engine_id\n" +
                   "INNER join generation ON generation_engine.generation_id = generation.generation_id\n" +
                   "INNER JOIN model_generation ON model_generation.generation_id = generation.generation_id\n" +
                   "INNER JOIN model ON model_generation.model_id = model.model_id\n" +
                   "WHERE model = :model and generation = :generation", nativeQuery = true)
    List<String> getEngineByModelAndGeneration(@Param("model") String model, @Param("generation") String generation);

    @Query(value = "SELECT exists(select 1 from engine where engine.engine = :engine)", nativeQuery = true)
    Long checkIfEngineExist(@Param("engine") String engine);

    @Query(value = "SELECT engine_id from engine WHERE engine = :engine", nativeQuery = true)
    Integer getEngineIdByEngine(@Param("engine") String engine);

    @Query(value = "SELECT COUNT(*) FROM generation_engine " +
            "WHERE generation_id = :generationId AND engine_id = :engineId", nativeQuery = true)
    Long checkIfGenerationEngineTableKeyExists(@Param("generationId") int generationId, @Param("engineId") int engineId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO engine (engine) values (:engine)", nativeQuery = true)
    void addVehicleEngine(@Param("engine") String engine);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO generation_engine (generation_id, engine_id) values (:generationId , :engineId)", nativeQuery = true)
    void addVehicleGenerationEngine(@Param("generationId") Integer generationId, @Param("engineId") Integer engineId);

}