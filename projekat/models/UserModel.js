const promisePool = require('../mySQLConnector');

async function getAllUsers() {
    try{
        const [rows] = await promisePool.query("SELECT * FROM user");
        return rows
    }catch (err) {
        console.error("Database error:", err);
        throw err;
  }
}

async function addUser(fullName, password, email, phoneNumber, role) {
    try{
        const [result] = await promisePool.query(
            "INSERT INTO user (full_name, email, password, phone_number , role) VALUES (?,?,?,?,?)",
            [fullName, email, password, phoneNumber, role]
        );
        console.log("user added sucessfully", result)
        return result.insertId;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
  }
}

async function getResponseByEmail(email) {
    try{
        const [result] = await promisePool.query(
            "SELECT EXISTS(SELECT 1 FROM user WHERE email = ?) AS found",
            [email]
        );
        console.log(result[0] === 1)
        return result[0].found === 1;
    }catch (err) {
        console.error("Database error:", err);
        throw err;
  }
}

async function getUserByEmail(email) {
    try{
        const [user] = await promisePool.query(
            "SELECT * FROM user WHERE email = ?",
            [email]
        );
        return user[0] || null
    }catch (err) {
        console.error("Database error:", err);
        throw err;
  }
}

async function getUserIdByEmail(email) {
    try{
        const [result] = await promisePool.query("SELECT id FROM user WHERE email = ?",
            [email]
        );
        return result[0].id
    }catch (err) {
        console.error("Database error:", err);
        throw err;
  }
}

module.exports = { 
    getAllUsers,
    addUser,
    getResponseByEmail,
    getUserByEmail,
    getUserIdByEmail
 };