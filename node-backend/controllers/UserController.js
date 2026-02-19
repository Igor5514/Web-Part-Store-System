const Users = require('../models/UserModel');

async function getAllUsers(req, res){
    try{
        const users = await Users.getAllUsers();
        return res.status(200).json(users);
    }catch(err){
        console.log("Error in user getAllUsers");
        return res.status(500).json({message: err.message});
    }
}

async function addUser(req,res ) {
    try{
        let { fullName, password, email, phoneNumber, role } = req.body;
        if (!fullName || !password || !email || !phoneNumber || !role) {
            return res.status(400).json({
                message: "All fields are required: fullName, password, email, phoneNumber, role"
            });
        }
        role = 'user';
        await Users.addUser(fullName, password, email, phoneNumber, role);

        return res.status(200).json({message: 'User added sucessfully!'});
    }catch(err){
        console.log("Error in user add");
        return res.status(500).json({message: err.message});
    }
}

async function getUserByEmail(req, res){
    try{
        const {email, password} = req.body;
        if(!email || !password){
            return res.status(400).json({
                message: "All fields are required: password, email"
            })
        }

        const user = await Users.getUserByEmail(email);
        if(!user){
            return res.status(404).json({
                message: "user not found"
            })
        }

        if(user.password === password){
            return res.status(200).json(user);
        }else{
            return res.status(401).json({
                message: 'passwords do not match failed to login'
            })
        }

    }catch(err){
        console.log("Error in getUserByEmail");
        return res.status(500).json({message: err.message});
    }
}

async function getResponseByEmail(req, res) {
    try{
        const email = req.body.email;
        console.log(email)
        if(!email){
            return res.status(400).json({
                message: "field email is required"
            });
        }
        const exists = await Users.getResponseByEmail(email);

        return res.status(200).json(exists);

    }catch(err){
        console.log("Error in user getResponseByEmail");
        return res.status(500).json({message: err.message});
    }
}

module.exports = {
    getAllUsers,
    addUser,
    getUserByEmail,
    getResponseByEmail
}