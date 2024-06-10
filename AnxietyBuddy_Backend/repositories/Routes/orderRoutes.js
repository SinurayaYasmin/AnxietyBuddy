const express = require('express');
const router = express.Router();
const { handleGetOrder, handleCreateOrder} = require ('../Handlers/orderHandle');

router.post('/createOrder/:userID', handleCreateOrder);
router.get('/getOrder', handleGetOrder);

module.exports = router;