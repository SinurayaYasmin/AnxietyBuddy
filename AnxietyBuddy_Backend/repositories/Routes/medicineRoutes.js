const express = require('express');
const router = express.Router();
const { handleAddMedicine, handleBuyMedicine, handleGetMedicine, handleRemoveMedicine, handleUpdateMedicine} = require ('../Handlers/medicineHandle');


router.post('/addMedicine', handleAddMedicine);
router.put('/updateMedicine', handleBuyMedicine);
router.get('/', handleGetMedicine);
router.delete('/removeMedicine', handleRemoveMedicine);
router.put('/updateQuantity', handleUpdateMedicine);




module.exports = router;