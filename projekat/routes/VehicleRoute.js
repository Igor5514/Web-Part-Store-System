const express = require('express');
const router = express.Router();
const vehicleController = require('../controllers/vehicleController');

const {
    validateDefault,
    validateEngine
} = require('../validators/VehicleValidator');

router.get('/getPartGroup', vehicleController.getPartGroup);
router.post('/getPartsList', validateDefault, vehicleController.getPartsList);

router.get('/getMake', vehicleController.getVehicleMake);
router.post('/getModel', vehicleController.getVehicleModel);
router.post('/getGeneration', validateDefault, vehicleController.getVehicleGeneration);
router.post('/getEngine', validateEngine, vehicleController.getVehicleEngine);

module.exports = router;




