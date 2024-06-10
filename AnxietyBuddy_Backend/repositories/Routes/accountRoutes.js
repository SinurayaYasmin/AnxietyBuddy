const express = require('express');
const router = express.Router();
const { handleRegister, handleLogin, handleGetByID, handleTopUp, handlePicture, handleForgotPassword} = require ('../Handlers/accountHandle');


router.post('/register', handleRegister);
router.post('/login', handleLogin);
router.get('/:userID', handleGetByID);
router.put('/topUp/:userID', handleTopUp);
router.put('/setPicture/:userID', handlePicture);
router.put('/forgotPassword', handleForgotPassword);



module.exports = router;