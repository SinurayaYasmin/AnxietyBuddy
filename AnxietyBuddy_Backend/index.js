const express = require ("express");
const cors = require('cors');
const bodyParser = require('body-parser');
const port = 4000;
const app = express();
const account = require('./repositories/Routes/accountRoutes');
const doctor = require('./repositories/Routes/doctorsRoutes');
const consule = require('./repositories/Routes/consuleRoutes');
const medicine = require('./repositories/Routes/medicineRoutes');
const recipe = require('./repositories/Routes/recipeRoutes');
const journal = require('./repositories/Routes/moodJournalRoutes');
const order = require('./repositories/Routes/orderRoutes');


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());



//Endpoint


//CREATE
app.use('/anxietyBuddy/account', account);
app.use('/anxietyBuddy/doctor', doctor);
app.use('/anxietyBuddy/consule', consule);
app.use('/anxietyBuddy/medicine', medicine);
app.use('/anxietyBuddy/recipe', recipe);
app.use('/anxietyBuddy/journal', journal);
app.use('/anxietyBuddy/order', order);

/*
app.post('/anxietyBuddy/registerAccount', account.registerAccount)
app.post('/anxietyBuddy/loginAccount', account.loginAccount)
app.post('/anxietyBuddy/writeJournal', journal.writeJournal)
app.post('/anxietyBuddy/registerDoctor', doctor.registerDoctors)
app.post('/anxietyBuddy/startConsule', consule.startConsule)
app.post('/anxietyBuddy/addMedicine', medicine.addMedicine)
app.post('/anxietyBuddy/createRecipe', recipe.createRecipe)


//GET
app.get('/anxietyBuddy/getAccount', account.getAccount);
app.get('/anxietyBuddy/getDoctor', doctor.getDoctor);
app.get('/anxietyBuddy/getMedicine', medicine.getMedicine);



//UPDATE
app.put('/anxietyBuddy/updateConsule', consule.updateConsule)
app.put('/anxietyBuddy/updateMedicine', medicine.updateMedicine);


//DELETE
app.delete('/anxietyBuddy/removeMedicine', medicine.removeMedicine);

*/
app.listen(port, ()=> {
    console.log("Server running on port ", port);
});



