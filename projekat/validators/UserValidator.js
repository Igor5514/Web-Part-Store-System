const { body, param, query, validationResult } = require("express-validator")

const addUserRules = [
    body("fullName")
      .notEmpty().withMessage("Full name is required")
      .isLength({ min: 3 }).withMessage("Full name must be at least 3 characters"),
    body("password")
      .notEmpty().withMessage("Password is required")
      .isLength({ min: 4 }).withMessage("Password must be at least 6 characters"),
    body("email")
      .notEmpty().withMessage("Email is required")
      .isEmail().withMessage("Invalid email address"),
    body("phoneNumber")
      .notEmpty().withMessage("Phone number is required")
      .withMessage("Invalid phone number"),
    body("role")
      .notEmpty().withMessage("Role is required")
]

const getUserByEmailRules = [
    body("email")
      .notEmpty().withMessage("Email is required")
      .isEmail().withMessage("Invalid email address"),
    body("password")
      .notEmpty().withMessage("Password is required")
      .isLength({ min: 4 }).withMessage("Password must be at least 6 characters")
]

const getResponseByEmailRules = [
    body("email")
      .notEmpty().withMessage("Email is required")
      .isEmail().withMessage("Invalid email address")
]


function validate(rules){
  return[
    ...rules, (req,res,next) => {
      const errors = validationResult(req);
      if(!errors.isEmpty()) return res.status(422).json({errors: errors.array()});
      next();
    }
  ]
}

const validateAddUser = validate(addUserRules);
const validateGetResponseByEmail = validate(getResponseByEmailRules);
const validateGetUserByEmail = validate(getUserByEmailRules);

module.exports = {
  validateAddUser,
  validateGetResponseByEmail,
  validateGetUserByEmail
}

