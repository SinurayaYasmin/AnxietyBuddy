const express = require('express');
const router = express.Router();
const {handleGetConsule, handleUpdateConsule, handleStartConsule, handleCloseConsule} = require ('../Handlers/consuleHandle');


router.post('/startConsule', handleStartConsule);
router.get('/:consuleID', handleGetConsule);
router.put('/updateConsule/:consuleID', handleUpdateConsule);
router.put('/closeConsule/:consuleID', handleCloseConsule);




module.exports = router;