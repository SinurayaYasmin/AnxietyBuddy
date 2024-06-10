const {writeJournal, getJournal}= require('../Models/moodJournal');

const handleWriteJournal = async (req, res) => {
    const {userID} = req.params;
    const {currentmood, text} = req.body;
    try {
        const journal = await writeJournal(userID, currentmood, text);
        return res.status(200).json({ message: 'Write jorunal successful', journal: journal });
    } catch (error) {
        return res.status(400).json({ message: 'Write  journal failed', error: error.message });
    }
};

const handleGetJournal = async (req, res) => {
    const {journalID} = req.body;
    try {
        const journal = await getJournal(journalID);
        return res.status(200).json({ message: 'Get journal successful', journal: journal });
    } catch (error) {
        return res.status(400).json({ message: 'Get journal failed', error: error.message });
    }
};

module.exports = { handleGetJournal, handleWriteJournal};