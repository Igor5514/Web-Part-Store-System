const partModel = require('../models/PartModel');
const vehicleModel = require('../models/VehicleModel');
const userModel =require('../models/UserModel');

function toCamelCase(str) {
  return str.replace(/_([a-z])/g, (_, char) => char.toUpperCase());
}

function convertToReadableJson(listOfObjects){
    return listOfObjects.map(element => {
      const newObj = {};
      for (const key in element) {
        newObj[toCamelCase(key)] = element[key];
      }
      return newObj;
    });
}

async function getPartsForSpecificVehicle(req, res) {
  try {
    const modelId = await vehicleModel.getModelIdByModel(req.body.model);
    const generationId = await vehicleModel.getGenerationIdByGeneration(req.body.generation);
    const engineId = await vehicleModel.getEngineIdByEngine(req.body.engine);
    const name = req.body.name;
    
    const parts = await partModel.getPartsForSpecificVehicle(modelId, generationId, engineId, name);

    const convertedParts = convertToReadableJson(parts);

    return res.status(200).json(convertedParts);
  } catch (err) {
    console.log("Error in getPartsForSpecificVehicle", err.message);
    return res.status(500).json({ error: err.message });
  }
}

async function postCartItem(req, res){
    try{
        const partId = req.query.partId
        const userId = await userModel.getUserIdByEmail(req.query.email);
        
        await partModel.postCartItem(userId, partId);
        
        return res.status(200).json({message: 'cart item added sucessfully!'});
    }catch(err){
        console.log("Error in getCartItems");
        return res.status(500).json({message: err.message});
    }
}

async function getCartItems(req, res){
    try{
        const email = req.query.email;
        const userId = await userModel.getUserIdByEmail(email);

        const parts = await partModel.getCartItems(userId);
        const convertedParts = convertToReadableJson(parts);
        
        return res.status(200).json(convertedParts);
    }catch(err){
        console.log("Error in getCartItems");
        return res.status(500).json({message: err.message});
    }
}


module.exports = {
    getPartsForSpecificVehicle,
    postCartItem,
    getCartItems
};