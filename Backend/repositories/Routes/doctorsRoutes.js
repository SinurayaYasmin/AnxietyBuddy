const express = require('express');
const router = express.Router();
const { handleGetDoctor, handleRegisterDoctor, handleUpdateDoctor} = require ('../Handlers/doctorsHandle');

router.post('/registerDoctor', handleRegisterDoctor);
router.get('/:doctorID', handleGetDoctor);
router.put('/:doctorID', handleUpdateDoctor);

module.exports = router;