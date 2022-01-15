/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Citizen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class CitizenDB {


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

            UtilitiesDB.setValues(preparedStatement,
                    citizen.getAmka(),
                    citizen.getVat(),
                    citizen.getFirst_name(),
                    citizen.getLast_name(),
                    citizen.getBirth_date(),
                    citizen.getGender(),
                    citizen.getUserName(),
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
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());

        }

    }

    public static void deleteCitizen(Citizen citizen) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM citizens WHERE USERID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(citizen.getUser_id()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }
    }

    public static Citizen getCitizen(int citizen_id) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        Citizen cit = null;
        try {
            con = CccDB.getConnection();
            String sel = "SELECT * FROM citizens WHERE USERID = ?";
            preparedStatement = con.prepareStatement(sel);
            preparedStatement.setInt(1, citizen_id);
            preparedStatement.executeQuery();

            ResultSet res = preparedStatement.getResultSet();
            if (res.next() == true) {
                cit = new Citizen();
                cit.setUser_id(res.getString("USERID"));
                cit.setAmka(res.getString("AMKA"));
                cit.setVat(res.getString("VAT"));
                cit.setFirst_name(res.getString("FIRST_NAME"));
                cit.setLast_name(res.getString("LAST_NAME"));
                cit.setBirth_date(res.getString("BIRTH_DATE"));
                cit.setGender(res.getString("GENDER"));
                cit.setUserName(res.getString("USERNAME"));
                cit.setPassword(res.getString("PASSWORD"));
                cit.setEmail(res.getString("EMAIL"));
                cit.setAddress(res.getString("ADDRESS"));
                cit.setPhone(res.getString("PHONE"));
                cit.setAmount_due(res.getString("AMOUNT_DUE"));
                cit.setAccount_number(res.getString("ACCOUNT_NUMBER"));
                cit.setCredit_limit(res.getString("CREDIT_LIMIT"));
                cit.setCredit_balance(res.getString("CREDIT_BALANCE"));
                cit.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));

            }
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return cit;

    }

    public static void updateCitizen(Citizen cit) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String update_cit_sql = "UPDATE citizens "
                    + "SET LAST_NAME = ? "
                    + "SET PHONE = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + " SET GENDER = ? "
                    + "SET PASSWORD = ? "
                    + "SET AMOUNT_DUE = ? "
                    + "SET CREDIT_LIMIT = ? "
                    + "SET CREDIT_BALANCE = ? "
                    + "SET ACCOUNT_DUE_DATE = ? "
                    + "WHERE USERID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(update_cit_sql);
            UtilitiesDB.setValues(preparedStatement, cit.getLast_name(),
                    cit.getPhone(), cit.getEmail(),
                    cit.getAddress(), cit.getGender(), cit.getPassword(),
                    cit.getAmount_due(), cit.getCredit_limit(), cit.getCredit_balance(),
                    cit.getAccount_due_date(), cit.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

    }

}