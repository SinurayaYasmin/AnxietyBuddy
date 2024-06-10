const {registerDoctors, getDoctorByID, updateDoctor}= require('../Models/doctors');

const handleRegisterDoctor = async (req, res) => {
    const {doctorID, doctorName, experience, speciality, workingplace} = req.body;
    try {
        const account = await registerDoctors(doctorID, doctorName, experience, speciality, workingplace);
        return res.status(200).json({ message: 'Update doctor`s detail success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Update doctor`s detail failed', error: error.message });
    }
};

const handleGetDoctor = async (req, res) => {
    const {doctorID} = req.params;
    try {
        const account = await getDoctorByID(doctorID);
        return res.status(200).json({ message: 'Get doctor`s detail success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Get doctor`s detail failed', error: error.message });
    }
};

const handleUpdateDoctor = async (req, res) => {
    const {doctorID} = req.params;
    const {experience} = req.body;
    try {
        const account = await updateDoctor(doctorID, experience);
        return res.status(200).json({ message: 'Update doctor`s detail success ', account: account });
    } catch (error) {
        return res.status(400).json({ message: 'Update doctor`s detail failed', error: error.message });
    }
};



module.exports = { handleRegisterDoctor, handleGetDoctor, handleUpdateDoctor};