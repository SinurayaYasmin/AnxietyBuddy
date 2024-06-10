const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})

const writeJournal = async (userID, currentmood, text) => {
    try {
            const result = await pool.query(
                    `insert into moodjournal_table (userid, currentmood, text) values ('${userID}', '${currentmood}', '${text}') returning *`
            );
            const journal = result.rows[0];
            if (journal.length !==0){
                return journal;
            } else {
                throw new Error('Failed To Get Journal');
            }
        }
        catch (error){
            throw error;
        }
}

const getJournal = async (journalID) => {
    try {
            const result = await pool.query(
                    `SELECT * FROM moodjournal_table where journalid = '${journalID}'`
            );

            const journal = result.rows;
            if (journal){
                return journal;
            } else {
                //throw new Error('Failed To Get Journal');
                return journal;
            }
        }
        catch (error){
            throw error;
        }
}
module.exports = { writeJournal, getJournal };