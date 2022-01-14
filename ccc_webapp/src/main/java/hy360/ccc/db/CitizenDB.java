/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Citizen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class CitizenDB {

    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    private static void closeConnection(PreparedStatement preparedStatement, Connection con) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
    }

    public static void addCitizen(Citizen citizen) {

        try {
            //  citizen.checkFields();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement("INSERT INTO citizens "
                    + "( `AMKA`, `VAT`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`,"
                    + " `USERNAME`, `PASSWORD`, `PHONE`, `EMAIL`, `ADDRESS`, `AMOUNT_DUE`,"
                    + " `ACCOUNT_NUMBER`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE` )"
                    + " VALUES (? ,? ,? ,? ,? ,?"
                    + ", ?, ?, ?, ?, ?, ?"
                    + ", ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            setValues(preparedStatement,
                    
                    
                    citizen.getAmka(),
                    citizen.getVat(),
                    citizen.getFirst_name(),
                    citizen.getLast_name(),
                    citizen.getBirth_date(),
                    citizen.getGender(),
                    citizen.getUsername(),
                    citizen.getPassword(),
                    citizen.getPhone(),
                    citizen.getEmail(),
                    citizen.getAddress(),
                    citizen.getAmount_due(),
                    citizen.getAccount_number(),
                    citizen.getCredit_limit(),
                    citizen.getCredit_balance(),
                    citizen.getAccount_due_date());

            preparedStatement.executeUpdate();


        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
        }

    }

    public static void deleteCitizen(Citizen citizen) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM citizens WHERE USERID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(citizen.getUserid()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
        }
    }


}