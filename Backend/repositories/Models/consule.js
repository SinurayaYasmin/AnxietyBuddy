const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})


const startConsule = async(doctorID, doctorName, patientID, patientName) => {
    try {
        const result = await pool.query(
            `INSERT INTO consule_table (doctorid, doctorname, patientid, patientname, message, status) VALUES ('${doctorID}', '${doctorName}', '${patientID}', '${patientName}', '{===START===}', 'ON GOING') RETURNING *`
        );


        const consule = result.rows[0];        
        if (consule.length !==0){
            return consule;
    } else {
            throw new Error ('Failed to start consule');
        }
    }
    catch (error){
        throw error;
    }

}

const getConsuleByID = async(consuleID) => {
    try {
        const result = await pool.query(
            `SELECT * from consule_table where consuleid = '${consuleID}'`
        );

        const consule = result.rows[0];        
        if (consule.length !==0){
            return consule;
    } else {
            throw new Error ('Failed to start consule');
        }
    }
    catch (error){
        throw error;
    }

}

const updateConsule = async(consuleID, message) => {
    try {
        const result = await pool.query(
            `UPDATE consule_table SET message = array_append(message, '${message}') WHERE consuleid = '${consuleID}' RETURNING *`
        );

        const consule = result.rows[0];        
        if (consule){
            return consule;
    } else {
            throw new Error ('Failed to update consule');
        }
    }
    catch (error){
        throw error;
    }

}

const closeConsule = async(consuleID) => {
    try {
        const result = await pool.query(
            `UPDATE consule_table SET status = 'FINISHED' WHERE consuleid = '${consuleID}' RETURNING *`
        );

        const consule = result.rows[0];        
        if (consule){
            return consule;
    } else {
            throw new Error ('Failed to update consule');
        }
    }
    catch (error){
        throw error;
    }

}
module.exports = {startConsule, updateConsule, getConsuleByID, closeConsule};