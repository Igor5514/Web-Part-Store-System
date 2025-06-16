package com.example.server_354.Services;

import com.example.server_354.object.Parts;
import com.example.server_354.object.Vehicle;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final EngineRepository engineRepository;

    public PartService(PartRepository partRepository, ModelRepository modelRepository, GenerationRepository generationRepository, EngineRepository engineRepository){
        this.partRepository = partRepository;
        this.modelRepository = modelRepository;
        this.generationRepository = generationRepository;
        this.engineRepository = engineRepository;
    }

    public List<Parts> getPartsForSpecificVehicle(Vehicle vehicle) {
        Integer modelId = modelRepository.getModelIdByModel(vehicle.getModel());
        Integer generationId = generationRepository.getGenerationIdByGeneration(vehicle.getGeneration());
        Integer engineId = engineRepository.getEngineIdByEngine(vehicle.getEngine());
        String partName = vehicle.getName();

        System.out.println(vehicle.getMake());
        System.out.println(modelId);
        System.out.println(generationId);
        System.out.println(engineId);

        return partRepository.getPartsForSpecificVehicle(modelId,generationId,engineId, partName);
    }
}
