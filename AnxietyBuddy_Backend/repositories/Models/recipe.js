const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})


const createRecipe = async (doctorid, patientid, medicinecode, medicinename) => {
    try {
        const result = await pool.query(
            `INSERT INTO recipe_table (doctorid, doctorname, patientid, patientname, medicinecodes, medicinenames)
SELECT
    d.doctorid,
    d.doctorname,
    u.userid,
    u.username,
    ARRAY['${medicinecode}'],  
    ARRAY['${medicinename}']          
FROM
    user_table u,
    doctors_table d
WHERE
    d.doctorid = '${doctorid}'
    AND u.userid = '${patientid}'
RETURNING *`
            
        );

        const recipe = result.rows;
        if (recipe){
            
            return recipe;
    } else {
            throw new Error ('Failed to create recipe');
        }
    }
    catch (error){
        throw error;
    }

}


const  getRecipe = async (recipeID) => {
    try {
        const result = await pool.query(
                `select * from recipe_table where recipeid = '${recipeID}'`
        );

        const recipe = result.rows;
        if (recipe){
            
            return recipe;
    } else {
            throw new Error ('Failed to get recipe');
        }
    }
    catch (error){
        throw error;
    }


}




module.exports = {createRecipe, getRecipe};