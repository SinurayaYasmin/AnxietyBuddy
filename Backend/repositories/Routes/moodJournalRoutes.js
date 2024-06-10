const express = require('express');
const router = express.Router();
const { handleGetJournal, handleWriteJournal} = require ('../Handlers/moodJournalHandle');

router.post('/writeJournal/:userID', handleWriteJournal);
router.get('/getJournal', handleGetJournal);

module.exports = router;