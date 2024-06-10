const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})

const registerDoctors = async(doctorID, doctorName, experience, speciality, workingplace) =>{
    try {
    
        const result = await pool.query(
            `INSERT INTO doctors_table (doctorid, doctorname, experience, speciality, workingplace) VALUES ('${doctorID}', '${doctorName}', '${experience}', '${speciality}', '${workingplace}') RETURNING *`
        );
        const doctor = result.rows[0];
        if (doctor.length !== 0) {
            return doctor;
        } else {
            throw new Error('Failed To Update Doctor`s Detail');
        }
    } catch (error) {
        throw error;
    }
}


const getDoctorByID = async (doctorID) => {
    try {
        const result = await pool.query(
            `SELECT d.workingid AS workingid, d.doctorid AS doctorid, d.doctorname AS doctorname, u.useremail AS doctoremail, d.experience AS experience, d.speciality AS speciality, d.workingplace AS workingplace FROM user_table u JOIN doctors_table d ON u.userid = d.doctorid WHERE userid = '${doctorID}'`
        );

        const doctor = result.rows[0];
        if (doctor.length !==0){
            return doctor;
    } else {
            throw new Error('Failed to get doctor`s detail by doctorID');
        }
    }
    catch (error){
        throw error;
    }

}

const updateDoctor = async (doctorID, experience) => {
    try {
        const result = await pool.query(
            `UPDATE doctors_table SET experience = array_append(experience, '${experience}') WHERE doctorid = '${doctorID}' RETURNING *`
        );
        const doctor = result.rows[0];
        if (doctor.length !==0){
            return doctor;
    }  else {
            throw new Error('Failed to update doctor`s detail by doctorID');
        }
    }
    catch (error){
        throw error;
    }

}


module.exports = {registerDoctors, getDoctorByID, updateDoctor};