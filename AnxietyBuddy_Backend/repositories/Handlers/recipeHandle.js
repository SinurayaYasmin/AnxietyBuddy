const {createRecipe, getRecipe}= require('../Models/recipe');

const handleCreateRecipe = async (req, res) => {
    const {doctorid, patientid, medicinecode, medicinename} = req.body;
    try {
        const recipe = await createRecipe(doctorid, patientid, medicinecode, medicinename);
        return res.status(200).json({ message: 'Create recipe successful', recipe: recipe });
    } catch (error) {
        return res.status(400).json({ message: 'Create recipe failed', error: error.message });
    }
};

const handleGetRecipe = async (req, res) => {
    const {recipeID} = req.body;
    try {
        const recipe = await getRecipe(recipeID);
        return res.status(200).json({ message: 'Get recipe successful', recipe: recipe });
    } catch (error) {
        return res.status(400).json({ message: 'Get recipe failed', error: error.message });
    }
};

module.exports = { handleCreateRecipe, handleGetRecipe};