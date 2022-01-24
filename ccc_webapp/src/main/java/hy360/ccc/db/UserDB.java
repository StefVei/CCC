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
                    + "( `USERNAME`, `PASSWORD`, `ACCOUNT_NUMBER`, `USER_TYPE`)"
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
    
    public static User getUser(String columnToSearch, String value) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        User user = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM users ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();

            ResultSet res = preparedStatement.getResultSet();
            if (res.next() == true) {
                user = new User();
                
                user.setUser_type(res.getString("USER_TYPE"));
                user.setUserName(res.getString("USERNAME"));
                user.setPassword(res.getString("PASSWORD"));
                user.setUser_id(res.getString("USERID"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return user;

    }

    public static void deleteUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "UPDATE users SET DELETED = ? WHERE USERID = ? ;";
            preparedStatement = con.prepareStatement(del);
            UtilitiesDB.setValues(preparedStatement, true, user.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, UserDB.class.getName());
        }
    }

    public static void updateUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String updateemployee_sql = "UPDATE users "
                    + "SET PASSWORD = ? "
                    + "WHERE USERID = ? ;";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updateemployee_sql);
            UtilitiesDB.setValues(preparedStatement, user.getPassword(),
                    user.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, UserDB.class.getName());
        }
    }

}
