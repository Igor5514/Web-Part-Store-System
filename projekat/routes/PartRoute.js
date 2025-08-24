const express = require('express')
const router = express.Router();
const partController = require('../controllers/PartController');

const {
    validateGetCartItem,
    validateGetPartsForSpecificVehicle,
    validatePostCartItem,
} = require('../validators/PartValidator');

router.get('/getCartItem', validateGetCartItem, partController.getCartItems);

router.post('/getPartsForVehicle', validateGetPartsForSpecificVehicle, partController.getPartsForSpecificVehicle);
router.post('/postCartItem', validatePostCartItem, partController.postCartItem);

module.exports = router;

