package com.example.server_354.Services;

import com.example.server_354.object.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

}