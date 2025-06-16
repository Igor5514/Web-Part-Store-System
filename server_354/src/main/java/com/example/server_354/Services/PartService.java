package com.example.server_354.Services;

import com.example.server_354.object.Parts;
import com.example.server_354.object.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final EngineRepository engineRepository;
    private final UserRepository userRepository;

    public PartService(PartRepository partRepository, ModelRepository modelRepository, GenerationRepository generationRepository, EngineRepository engineRepository, UserRepository userRepository){
        this.partRepository = partRepository;
        this.modelRepository = modelRepository;
        this.generationRepository = generationRepository;
        this.engineRepository = engineRepository;
        this.userRepository = userRepository;
    }

    public List<Parts> getPartsForSpecificVehicle(Vehicle vehicle) {
        Integer modelId = modelRepository.getModelIdByModel(vehicle.getModel());
        Integer generationId = generationRepository.getGenerationIdByGeneration(vehicle.getGeneration());
        Integer engineId = engineRepository.getEngineIdByEngine(vehicle.getEngine());
        String partName = vehicle.getName();

        return partRepository.getPartsForSpecificVehicle(modelId,generationId,engineId, partName);
    }

    public void postCartItem(Integer partId, String email) {
        Integer userId = userRepository.getUserIdByEmail(email);
        partRepository.postCartItem(partId,userId);
    }

    public List<Parts> getCartItems(String email) {
        Integer userId = userRepository.getUserIdByEmail(email);
        return partRepository.getCartItemsByUserId(userId);
    }
}
