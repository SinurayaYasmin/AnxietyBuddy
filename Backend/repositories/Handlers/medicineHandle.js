const {addMedicine, buyMedicine, removeMedicine, updateMedicine, getAllMedicine} = require("../Models/medicine");



const handleAddMedicine = async (req, res) => {
    const {medicinecode, medicinename, price, description, dosage, quantity, type} = req.body;
    try {
        const medicine = await addMedicine(medicinecode, medicinename, price, description, dosage, quantity, type);
        return res.status(200).json({ message: 'Add medicine successful', medicine: medicine });
    } catch (error) {
        return res.status(400).json({ message: 'Add medicine failed', error: error.message });
    }
};

const handleBuyMedicine = async (req, res) => {
    const {identifier, quantity} = req.body;
    try {
        const medicine = await buyMedicine(identifier, quantity);
        return res.status(200).json({ message: 'Subtract quantity successful', medicine: medicine });
    } catch (error) {
        return res.status(400).json({ message: 'Subtract quantity failed', error: error.message });
    }
};
const handleGetMedicine = async (req, res) => {
    try {
        const medicine = await getAllMedicine();
        return res.status(200).json({ message: 'Get all medicine successful', medicine: medicine });
    } catch (error) {
        return res.status(400).json({ message: 'Get all medicine failed', error: error.message });
    }
};
const handleRemoveMedicine = async (req, res) => {
    const {identifier} = req.body;
    try {
        const medicine = await removeMedicine(identifier);
        return res.status(200).json({ message: 'Remove medicine successful', medicine: medicine });
    } catch (error) {
        return res.status(400).json({ message: 'Remove medicine failed', error: error.message });
    }
};
const handleUpdateMedicine = async (req, res) => {
    const {identifier, quantity} = req.body;
    try {
        const medicine = await updateMedicine(identifier, quantity);
        return res.status(200).json({ message: 'Add quantity successful', medicine: medicine });
    } catch (error) {
        return res.status(400).json({ message: 'Add quantity failed', error: error.message });
    }
};

module.exports = { handleAddMedicine, handleBuyMedicine, handleGetMedicine, handleRemoveMedicine, handleUpdateMedicine};