package com.example.server_354.Services;

import com.example.server_354.object.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface GenerationRepository extends JpaRepository<Generation, Long> {


    @Query(value = "SELECT generation from generation \n" +
                   "INNER JOIN model_generation ON generation.generation_id = model_generation.generation_id\n" +
                   "INNER JOIN model ON model_generation.model_id = model.model_id\n" +
                   "WHERE model = :model", nativeQuery = true)
    List<String> getGenerationByModelId(@Param("model") String model);

    @Query(value = "SELECT generation_id from generation WHERE generation = :generation", nativeQuery = true)
    Integer getGenerationIdByGeneration(@Param("generation") String generation);

    @Query(value = "SELECT exists(select 1 from generation where generation.generation = :generation)", nativeQuery = true)
    Long checkIfGenerationExist(@Param("generation") String generation);

    @Query(value = "SELECT COUNT(*) \n" +
            "FROM model_generation \n" +
            "WHERE model_id = :modelId AND generation_id = :generationId;", nativeQuery = true)
    Long checkIfModelGenerationTableKeyExists(@Param("modelId") int modelId,@Param("generationId") int generationId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO generation (generation) values (:generation)", nativeQuery = true)
    void addVehicleGeneration(@Param("generation") String generation);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO model_generation (model_id, generation_id) values (:modelId, :generationId)", nativeQuery = true)
    void addVehicleModelGeneration(@Param("modelId") Integer modelId, @Param("generationId") Integer generationId);

}

