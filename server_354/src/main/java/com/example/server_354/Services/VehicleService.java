package com.example.server_354.Services;

import com.example.server_354.object.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final EngineRepository engineRepository;
    private final PartGroupRepository partGroupRepository;
    private final PartTypeRepository partTypeRepository;

    public VehicleService(MakeRepository makeRepository, ModelRepository modelRepository, GenerationRepository generationRepository, EngineRepository engineRepository, PartGroupRepository partGroupRepository, PartTypeRepository partTypeRepository) {
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.generationRepository = generationRepository;
        this.engineRepository = engineRepository;
        this.partGroupRepository = partGroupRepository;
        this.partTypeRepository = partTypeRepository;
    }

    public List<String> getModelByMakeId(int makeId){
        return modelRepository.getModelByMakeId(makeId);
    }

    public Integer getMakeIdByMake(String make){
        return makeRepository.getMakeIdByMake(make);
    }

    public List<String> getGenerationByModelId(String model){
        return generationRepository.getGenerationByModelId(model);
    }

    public List<String> getEngineByModelAndGeneration(String model, String generation){
        int modelId = modelRepository.getModelIdByModel(model);
        int generationId = generationRepository.getGenerationIdByGeneration(generation);
        return engineRepository.getEngineByModelAndGeneration(modelId, generationId);
    }

    public List<CarPartGroup> getPartsGroup(){
        return partGroupRepository.findAll();
    }

    public List<String> getFourCarPartsByGroupId(int groupId){
        return partGroupRepository.getFourPartsByGroupName(groupId);
    }

    public int getGroupIdByGroupName(String groupName){
        return partGroupRepository.getGroupIdByGroupName(groupName);
    }

    public List<Make> getEveryMake(){
        return makeRepository.findAll();
    }

    public List<ModelObject> getEveryModel(){
        return modelRepository.getModelObject();
    }

    public List<Generation> getEveryGeneration(){
        return generationRepository.findAll();
    }

    public List<Engine> getEveryEngine(){
        return engineRepository.findAll();
    }

    public List<CarPartType> getPartsTypeByGroupId(int groupId){
        return partTypeRepository.getPartsTypeByGroupName(groupId);
    }

    public Boolean checkIfMakeExist(String make){
        return makeRepository.checkIfMakeExist(make) == 1;
    }

    public Boolean checkIfModelExist(String model){
        return modelRepository.checkIfModelExist(model) == 1 ? true : false;
    }

    public Boolean checkIfGenerationExist(String generation){
        return generationRepository.checkIfGenerationExist(generation) == 1 ? true : false;
    }

    public Boolean checkIfEngineExists(String engine){
        return engineRepository.checkIfEngineExist(engine) == 1 ? true : false;
    }

    public void addVehicleMake(String make, boolean makeExist) {
        if(!makeExist){
            makeRepository.addVehicleMake(make);
        }
    }

    public void addVehicleModel(String make, String model, boolean modelExist) {
        if(!modelExist){
            Integer makeId = makeRepository.getMakeIdByMake(make);
            modelRepository.addVehicleModel(makeId, model);
        }
    }

    public void addVehicleGeneration(String model, String generation, boolean generationExist) {
        if(!generationExist){
            generationRepository.addVehicleGeneration(generation);
        }

        checkAndAddModelGeneration(model, generation);
    }

    public void checkAndAddModelGeneration(String model, String generation){
        Integer modelId = modelRepository.getModelIdByModel(model);
        Integer generationId = generationRepository.getGenerationIdByGeneration(generation);
        if(!(generationRepository.checkIfModelGenerationTableKeyExists(modelId, generationId) >= 1)){
            generationRepository.addVehicleModelGeneration(modelId, generationId);
        }
    }

    public void addVehicleEngine(String model, String generation, String engine, boolean engineExist) {
        if(!engineExist){
            engineRepository.addVehicleEngine(engine);
        }
        checkAndAddGenerationEngine(model, generation, engine);
    }

    public void checkAndAddGenerationEngine(String model, String generation, String engine){
        int modelId = modelRepository.getModelIdByModel(model);
        int generationId = generationRepository.getGenerationIdByGeneration(generation);
        int engineId = engineRepository.getEngineIdByEngine(engine);
        if(!(engineRepository.checkIfModelGenerationEngineTableKeyExists(modelId, generationId, engineId) >= 1)){
            engineRepository.addVehicleModelGenerationEngine(modelId, generationId, engineId);
        }
    }

}
