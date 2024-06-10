const {pool} = require ('../config/db');

pool.connect().then(() =>{
    console.log("Connected To AnxietyBuddy PSQL Database ");
})


const registerAccount = async(username, useremail, userpassword, gender,  usertype) => {
    try {
        const result = await pool.query(
            `INSERT INTO user_table (username, useremail, gender, userpassword, usertype) values ('${username}', '${useremail}', '${gender}', '${userpassword}', '${usertype}') RETURNING *`
        );
        const account = result.rows[0];
        if (account.length !==0){
            return account;
        } else {
            throw new Error('Failed To Register');
        }
    }
    catch (error){
        throw error;
    }
}

const loginAccount = async (emailorname, password) => {
    try {
        const result = await pool.query(
             
            `SELECT * FROM user_table WHERE (username = '${emailorname}' OR useremail = '${emailorname}') AND userpassword = '${password}'`
        );
        
        const account = result.rows;
        if (account.length !== 0){
            return account;
    } else {
        throw new Error('Failed To Login');
        }
    }
    catch (error){
     throw error;
    }

}


const getAccountByID = async (userID) => {
    try {
        const result = await pool.query(
            `SELECT * FROM user_table WHERE userid = '${userID}'`
        );

        const account = result.rows;
        if (account !==0){
            return account;
    } else {
            throw new Error('Failed to get account');
        }
    }
    catch (error){
        throw error;
    }
}


const topUp = async (userID, balance) => {
    try {
        const result = await pool.query(
            `UPDATE user_table SET userbalance = userbalance + ${balance} WHERE userid = '${userID}' RETURNING *`
        );
        return result.rows[0];
    } catch (error) {
        throw error;
    }
};

const setPicture = async (userID, imageUrl) => {
    try {
      const result = await pool.query(
        `UPDATE user_table SET profile_picture = '${imageUrl}' WHERE userid = '${userID}' RETURNING *`
      );
      if (result.rows.length === 0) {
        throw new Error('Update picture failed');
      }
      return result.rows[0];
    } catch (error) {
      throw error;
    }
  };

  const forgotPassword = async (emailorname, newPassword) => {
    try {
      const result = await pool.query(
        `UPDATE user_table SET userpassword = '${newPassword}' WHERE username = '${emailorname}' OR useremail = '${emailorname}' RETURNING *`,
      );
      if (result.rows.length === 0) {
        throw new Error('Account not found');
      }
      return result.rows[0];
    } catch (error) {
      throw error;
    }
  };




module.exports = {registerAccount, loginAccount, getAccountByID, topUp, setPicture, forgotPassword};