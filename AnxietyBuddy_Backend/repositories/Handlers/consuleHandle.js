const {startConsule, getConsuleByID, updateConsule, closeConsule}= require('../Models/consule');

const handleStartConsule = async (req, res) => {
    const {doctorID, doctorName, patientID, patientName} = req.body;
    try {
        const account = await startConsule(doctorID, doctorName, patientID, patientName);
        return res.status(200).json({ message: 'Start consule success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Start consule failed', error: error.message });
    }
};

const handleUpdateConsule = async (req, res) => {
    const {consuleID} = req.params;
    const {message} = req.body;
    try {
        const account = await updateConsule(consuleID, message);
        return res.status(200).json({ message: 'Update consule success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'update consule failed', error: error.message });
    }
};

const handleGetConsule = async (req, res) => {
    const {consuleID} = req.params;
    try {
        const account = await getConsuleByID(consuleID);
        return res.status(200).json({ message: 'Get consule success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Get consule failed', error: error.message });
    }
};

const handleCloseConsule = async (req, res) => {
    const {consuleID} = req.params;
    try {
        const account = await closeConsule(consuleID);
        return res.status(200).json({ message: 'Close consule success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Close consule failed', error: error.message });
    }
};


module.exports = { handleStartConsule, handleGetConsule, handleUpdateConsule, handleCloseConsule};