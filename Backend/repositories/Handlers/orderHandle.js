const {createOrder, getOrder}= require('../Models/order');

const handleCreateOrder = async (req, res) => {
    const {medicineCode, quantity} = req.body;
    const {userID} = req.params;

    try {
        const account = await createOrder(userID, medicineCode, quantity);
        return res.status(200).json({ message: 'Create order successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Create order failed', error: error.message });
    }
};

const handleGetOrder = async (req, res) => {
    const {orderID} = req.body;

    try {
        const account = await getOrder(orderID);
        return res.status(200).json({ message: 'Get order successful', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Get order failed', error: error.message });
    }
};

module.exports = { handleCreateOrder, handleGetOrder };