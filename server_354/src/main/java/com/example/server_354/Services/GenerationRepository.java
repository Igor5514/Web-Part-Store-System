package com.example.server_354.Services;

import com.example.server_354.object.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface GenerationRepository extends JpaRepository<Generation, Long> {


    @Query(value = "SELECT generation from generation \n" +
                   "INNER JOIN model_generation ON generation.generation_id = model_generation.generation_id\n" +
                   "INNER JOIN model ON model_generation.model_id = model.model_id\n" +
                   "WHERE model = :model", nativeQuery = true)
    List<String> getGenerationByModelId(@Param("model") String model);

    @Query(value = "SELECT exists(select 1 from generation where generation.generation = :generation)", nativeQuery = true)
    Long checkIfGenerationExist(@Param("generation") String generation);
}

