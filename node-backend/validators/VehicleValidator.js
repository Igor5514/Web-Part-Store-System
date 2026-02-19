const {body, param, query, validationResult} = require('express-validator');

const engineRules = [
  body('model').trim().notEmpty()
    .withMessage('You must provide model'),
  body('generation').trim().notEmpty()
    .withMessage('You must provide generation'),

];

const defaultRules = [
  body().custom(body => {
    for (const [key, value] of Object.entries(body)) {
      if (typeof value === "string" && value.trim() === "") {
        throw new Error(`You must provide "${key}"`);
      }
    }
    return true;
  })
];

function validate(rules){
    return[
        ...rules,
        (req,res,next) => {
            const errors = validationResult(req);
            if(!errors.isEmpty()) return res.status(422).json({errors: errors.array()});
            next();
        }
    ];
}

const validateDefault = validate(defaultRules);
const validateEngine = validate(engineRules);


module.exports = {
    validateDefault,
    validateEngine
}