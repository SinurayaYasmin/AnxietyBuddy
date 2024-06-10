const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})


const addMedicine = async (medicinecode, medicinename, price, description, dosage, quantity, type) => {
    try {
        const result = await pool.query(
            `INSERT INTO medicine_table VALUES ('${medicinecode}', '${medicinename}', '${price}', '${description}', '${dosage}', '${quantity}', '${type}') RETURNING *`
        );

        const medicine = result.rows[0];
        if (medicine !==0){            
            return medicine;

    } else {
            throw new Error ('Failed to add medicine');
        }
    }
    catch (error){
        throw error;
    }

}

    /*const getMedicine =  async(identifier) => {
    try {
        const result = await pool.query(
            `SELECT * FROM medicine_table WHERE medicinecode = '${identifier}' or medicinename ='${identifier}'`
        );

        const medicine = result.rows[0];
        if (medicine !==0){            
            return medicine;

    } else {
            throw new Error ('Failed to get medicine');
        }
    }
    catch (error){
        throw error;
    }

}*/

const getAllMedicine =  async() => {
    try {
        const result = await pool.query(
            `SELECT * FROM medicine_table`
        );

        const medicine = result.rows;
        if (medicine !==0){            
            return medicine;

    } else {
            throw new Error ('Failed to get all medicine');
        }
    }
    catch (error){
        throw error;
    }

}

const buyMedicine =  async(identifier, quantity) => {
    try {
        const result = await pool.query(

            `UPDATE medicine_table SET quantity = GREATEST (quantity - '${quantity}', 0) WHERE medicinename = '${identifier}' or medicinecode = '${identifier}'`
        );
        const medicine = result.rows[0];
        if (medicine !==0){            
            return medicine;

    } else {
            throw new Error ('Failed to buy medicine');
        }
    }
    catch (error){
        throw error;
    }

}


const removeMedicine = async (identifier) =>{
    try {
        const result = await pool.query(
            `DELETE FROM medicine_table WHERE medicinecode = '${identifier}' or medicinename = '${identifier}'`
        );

        const medicine = result.rows[0];
        if (medicine ===0){            
            return medicine;

    } else {
            throw new Error ('Failed to remove medicine');
        }
    }
    catch (error){
        throw error;
    }

}

const updateMedicine = async (identifier, quantity) => {
    try {
        const result = await pool.query(
            `UPDATE medicine_table SET quantity = '${quantity}' WHERE medicinecode = '${identifier}' or medicinename = '${identifier}' RETURNING *`
    
        );
        const medicine = result.rows[0];
        if (medicine !==0){            
            return medicine;

    } else {
            throw new Error ('Failed to update quantity of medicine');
        }
    }
    catch (error){
        throw error;
    }


}




module.exports = {addMedicine, buyMedicine, removeMedicine, updateMedicine, getAllMedicine};