const promisePool = require('../mySQLConnector');

async function getVehicleMake(){
    try{
        const [rows] = await promisePool.query("SELECT * FROM make");
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getMakeIdByMake(make){
     try{
        const [result] = await promisePool.query("SELECT make_id from make WHERE make = ?"
            , [make]
        );
        return result[0].make_id;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getModelIdByModel(model){
     try{
        const [result] = await promisePool.query("SELECT model_id from model WHERE model = ?"
            , [model]
        );
        return result[0].model_id;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getGenerationIdByGeneration(generation){
     try{
        const [result] = await promisePool.query("SELECT generation_id from generation WHERE generation = ?"
            , [" "+generation]
        );
        return result[0].generation_id;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getEngineIdByEngine(engine){
     try{
        const [result] = await promisePool.query("SELECT engine_id from engine WHERE engine = ?"
            , [engine]
        );
        return result[0].engine_id;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getModelByMakeId(makeId){
     try{
        const [rows] = await promisePool.query("SELECT model from model where make_id = ?"
            , [makeId]
        );
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getVehicleGenerationByModel(model){
     try{
        const [rows] = await promisePool.query("SELECT generation from generation \n" +
                   "INNER JOIN model_generation ON generation.generation_id = model_generation.generation_id\n" +
                   "INNER JOIN model ON model_generation.model_id = model.model_id\n" +
                   "WHERE model = ?"
            , [model]
        );
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}



async function getVehicleEngineByGenerationAndModel(modelId, generationId){
     try{
        console.log(modelId);
        console.log(generationId);
        const [rows] = await promisePool.query(
                    "SELECT e.engine FROM engine e \n" +
                    "JOIN model_generation_engine mge ON e.engine_id = mge.engine_id \n" +
                    "WHERE mge.model_id = ? AND mge.generation_id = ?"
            , [modelId, generationId]
        );
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getPartsGroup(){
      try{
        const [rows] = await promisePool.query("SELECT * FROM car_part_group");
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getFourCarPartsByGroupId(groupId){
      try{
        const [rows] = await promisePool.query("SELECT part_type_name FROM car_part_type " +
                                                "WHERE group_id = ? " +
                                                "LIMIT 4", [groupId]);
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getGroupIdByGroupName(groupName){
    try{
        const [rows] = await promisePool.query("SELECT group_id FROM car_part_group " +
                                               "WHERE group_name = ?", [groupName]);
        return rows[0].group_id;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getPartsTypeByGroupId(groupId){
    try{
        const [rows] = await promisePool.query("SELECT * FROM car_part_type " +
                                               "WHERE group_id = ?", [groupId]);
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

module.exports = {
    getVehicleMake,
    getMakeIdByMake,
    getModelByMakeId,
    getModelIdByModel,
    getGenerationIdByGeneration,
    getEngineIdByEngine,
    getVehicleGenerationByModel,
    getVehicleEngineByGenerationAndModel,
    getPartsGroup,
    getFourCarPartsByGroupId,
    getGroupIdByGroupName,
    getPartsTypeByGroupId
}