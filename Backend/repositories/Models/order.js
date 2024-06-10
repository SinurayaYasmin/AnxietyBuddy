const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})


const createOrder = async(userID, medicineCode, quantity) => {
    try {
        const result = await pool.query(
           `INSERT INTO order_table (patientid, patientname, medicinecode, quantity, totalprice, status, orderdate, medicinename)
SELECT
    u.userid,
    u.username,
    m.medicinecode,
        ${quantity},
    m.price * ${quantity},
    'DONE', 
     CURRENT_DATE,
    m.medicinename
FROM
    user_table u,
    medicine_table m
WHERE
    u.userid = '${userID}'
    AND m.medicinecode = '${medicineCode}'
RETURNING *`

        );
        const order = result.rows[0];
        if (order){
            return order;
        } else {
            throw new Error('Failed To create order');
        }
    }
    catch (error){
        throw error;
    }
}

    const getOrder = async(orderID) => {
        try {
            const result = await pool.query(
               `SELECt * from order_table where orderid = '${orderID}'`
            );
            const order = result.rows[0];
            if (order){
                return order;
            } else {
                throw new Error('Failed To get order');
            }
        }
        catch (error){
            throw error;
        }

}

module.exports = {createOrder, getOrder};