const express = require('express');
const router = express.Router();
const userController = require('../controllers/UserController');

const {
    validateAddUser,
    validateGetResponseByEmail,
    validateGetUserByEmail
} = require('../validators/UserValidator');

router.get('/getAllUsers', userController.getAllUsers);

router.post('/add', validateAddUser, userController.addUser);
router.post('/getByEmail', validateGetUserByEmail, userController.getUserByEmail);
router.post('/getResByEmail', validateGetResponseByEmail, userController.getResponseByEmail);

module.exports = router;