const express = require('express');
const router = express.Router();
const {handleCreateRecipe, handleGetRecipe} = require ('../Handlers/recipeHandle');


router.post('/createRecipe', handleCreateRecipe);
router.get('/getRecipe', handleGetRecipe);



module.exports = router;