const {body,param, query, validationResult} = require('express-validator')

const getPartsForSpecificVehicleRules = [
    body('make').trim().notEmpty()
    .withMessage('make is required'),
    body('model').trim().notEmpty()
    .withMessage('model is required'),
    body('generation').trim().notEmpty()
    .withMessage('generation is required'),
    body('engine').trim().notEmpty()
    .withMessage('engine is required'),
    body('name').trim().notEmpty()
    .withMessage('name is required')
]

const postCartItemRules = [
    param('email').trim().notEmpty().isEmail()
    .withMessage('email is required'),
    param('partId').isInt({min:0})
    .withMessage('partId must be equal or greater than 0')
]

const getCartItemsRules = [
    query('email').trim().notEmpty().isEmail()
    .withMessage('email is required')
]

function validator(rules){
    return[
        ...rules,
        (req, res, next) => {
            const errors = validationResult(req);
            if(!errors.isEmpty()) return res.status(422).json({errors: errors.array()});
            next();
        }
    ]
}

const validateGetPartsForSpecificVehicle = validator(getPartsForSpecificVehicleRules);
const validatePostCartItem = validator(postCartItemRules);
const validateGetCartItem = validator(getCartItemsRules);

module.exports = {
    validateGetPartsForSpecificVehicle,
    validatePostCartItem,
    validateGetCartItem
}