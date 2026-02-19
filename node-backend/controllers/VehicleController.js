const { buffer } = require('stream/consumers');
const vehicleModel = require('../models/VehicleModel');
const { response } = require('express');

async function getVehicleMake(req, res){
    try{
        const makeList = await vehicleModel.getVehicleMake();
        return res.status(200).json(makeList);
    }catch(err){
        console.log("Error in getVehicleMake");
        return res.status(500).json({error: err.message});
    }
}

async function getVehicleModel(req, res){
    try{
        let make = req.body.make;
        
        const makeId = await vehicleModel.getMakeIdByMake(make);
        const modelList = await vehicleModel.getModelByMakeId(makeId);
        
        const modelNames = modelList.map(obj => obj.model);
        return res.status(200).json(modelNames);
    }catch(err){
        console.log("Error in getVehicleModel");
        return res.status(500).json({error: err.message});
    }
}

async function getVehicleGeneration(req, res){
    try{
        const model = req.body.model;
        const generationList = await vehicleModel.getVehicleGenerationByModel(model);
        const generationNames = generationList.map(obj => obj.generation);
        return res.status(200).json(generationNames);
    }catch(err){
        console.log("Error in getVehicleGeneration");
        return res.status(500).json({error: err.message});
    }
}

async function getVehicleEngine(req, res){
    try{
        const model = req.body.model;
        const generation = req.body.generation;

        const modelId = await vehicleModel.getModelIdByModel(model);
        const generationId = await vehicleModel.getGenerationIdByGeneration(generation);

        const engineList = await vehicleModel.getVehicleEngineByGenerationAndModel(modelId, generationId);
        const engineNames = engineList.map(obj => obj.engine);

        return res.status(200).json(engineNames)
    }catch(err){
        console.log("Error in getVehicleEngine", err.message);
        return res.status(500).json({error: err.message});
    }
}

async function getPartGroup(req, res) {
  try {
    const loadedGroup = await vehicleModel.getPartsGroup();
    let groupList = [];

    const batchSize = 5;

    for(let i = 0; i< loadedGroup.length; i+= batchSize){
        const batch = loadedGroup.slice(i, i+batchSize);

        const results = await Promise.all(
            batch.map(async (element) => {
                const groupId = element.group_id;
                const image = element.image ? element.image.toString("base64") : null;
                let finalPartsList = [];
                const partsList = await vehicleModel.getFourCarPartsByGroupId(groupId);
                partsList.map(obj => finalPartsList.push(obj.part_type_name));
                return{
                    groupName: element.group_name,
                    groupImage: image,
                    partsList: finalPartsList
                };
            })
        )
        groupList.push(...results);
    }

    return res.status(200).json(groupList);
  } catch (err) {
    console.error("Error in getPartGroup", err);
    return res.status(500).json({ error: err.message });
  }
}

async function getPartsList(req, res){
    try{
        const groupName = req.body.groupName;
        const groupId = await vehicleModel.getGroupIdByGroupName(groupName);
        console.log(groupId);
        const partsList = await vehicleModel.getPartsTypeByGroupId(groupId);
        let finalPartsList = [];

        for(let i = 0; i< partsList.length; i++){
            const element = partsList[i];
            const image = element.image ? element.image.toString("base64") : null;
            
            const partTypeName = element.part_type_name;
            finalPartsList.push({
                partName: partTypeName,
                groupImage: image
            })
        }

        return res.status(200).json(finalPartsList);
    }catch(err){
        console.log("Error in getPartsList");
        return res.status(500).json({error: err.message});
    }
}

module.exports = {
    getVehicleMake,
    getVehicleModel,
    getVehicleGeneration,
    getVehicleEngine,
    getPartGroup,
    getPartsList
}