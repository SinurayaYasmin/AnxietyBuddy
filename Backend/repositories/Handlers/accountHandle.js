const {registerAccount, loginAccount, getAccountByID, topUp, setPicture, forgotPassword}= require('../Models/account');


const handleRegister = async (req, res) => {
    const {username, useremail, userpassword, gender,  usertype} = req.body;
    console.log(username);
    try {
        const account = await registerAccount(username, useremail, userpassword, gender,  usertype);
        return res.status(200).json({ message: 'Register successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Register failed', error: error.message });
    }
};

const handleLogin = async (req, res) => {
    const {emailorname, password} = req.body;
    try {
        const account = await loginAccount(emailorname, password);
        return res.status(200).json({ message: 'Login successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Login failed', error: error.message });
    }
};

const handleGetByID = async (req, res) => {
    const {userID} = req.params;
    try {
        const account = await getAccountByID(userID);
        return res.status(200).json({ message: 'Get account successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Get account failed', error: error.message });
    }
};

const handleTopUp = async (req, res) => {
    const {userID} = req.params;
    const {balance} = req.body;
    try {
        const account = await topUp(userID, balance);
        return res.status(200).json({ message: 'Top up successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Top up failed', error: error.message });
    }
};

const handlePicture = async (req, res) => {
    const {userID} = req.params;
    const {imageUrl} = req.body;
    try {
        const account = await setPicture(userID, imageUrl);
        return res.status(200).json({ message: 'Set profile picture successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Set [rofile picture failed', error: error.message });
    }
};

const handleForgotPassword = async (req, res) => {
    const {emailorname, newPassword} = req.body;
    try {
        const account = await forgotPassword(emailorname, newPassword);
        return res.status(200).json({ message: 'Change password successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Change password failed', error: error.message });
    }
};


module.exports = { handleRegister, handleLogin, handleGetByID, handleTopUp, handlePicture, handleForgotPassword};