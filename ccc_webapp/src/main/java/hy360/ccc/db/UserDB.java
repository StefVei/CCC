/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class UserDB {

    public static void addUser(User user) {

        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO users "
                    + "( `USERNAME`, `PASSWORD`, `ACCOUNT_NUMBER`, `USERTYPE`"
                    + " VALUES (? ,? ,? ,? )", PreparedStatement.RETURN_GENERATED_KEYS);

            UtilitiesDB.setValues(preparedStatement, user.getUserName(),
                    user.getPassword(), user.getAccount_number(),
                    user.getUser_type());

            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                user.setUser_id(set.getString(1));
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, UserDB.class.getName());
        }

    }
}
