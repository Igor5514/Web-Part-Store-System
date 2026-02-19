const promisePool = require('../mySQLConnector');

async function getPartsForSpecificVehicle(modelId, generationId, engineId, name){
    try{
        const [rows] = await promisePool.query("SELECT p.part_id, p.name, p.part_number, p.brand, p.price, " +
                                                "p.stock_quantity, p.image, p.description, p.weight, " +
                                                "p.dimensions, p.vehicle_type, p.date_added, p.last_updated " +
                                                "FROM parts p " +
                                                "INNER JOIN part_category pc ON p.part_id = pc.part_id " +
                                                "WHERE pc.model_id = ? " +
                                                "AND pc.generation_id = ? " +
                                                "AND pc.engine_id = ? " +
                                                "AND p.name = ?",
                                            [modelId, generationId, engineId, name]);
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function getCartItems(userId){
    try{
        const [rows] = await promisePool.query("SELECT p.* FROM parts p " +
                                                "INNER JOIN cart c ON c.part_id = p.part_id " +
                                                "WHERE c.user_id = ?",
                                            [userId]);
        return rows;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

async function postCartItem(userId, partId){
    try{
        const [rows] = await promisePool.query("INSERT INTO cart (user_id, part_id) values(? , ?)",
                                            [userId, partId]);
    }catch (err) {
        console.error("Database error:", err);
        throw err;
    }
}

module.exports = {
    getPartsForSpecificVehicle,
    getCartItems
}