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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                    + "( `USERID`, `AMKA`, `VAT`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`,"
                    + " `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`,"
                    + " `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE` )"
                    + " VALUES (?, ? ,? ,? ,? ,? ,?"
                    + ", ?, ?, ?, ?"
                    + ", ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            UtilitiesDB.setValues(preparedStatement,
                    citizen.getUser_id(),
                    citizen.getAmka(),
                    citizen.getVat(),
                    citizen.getFirst_name(),
                    citizen.getLast_name(),
                    citizen.getBirth_date(),
                    citizen.getGender(),
                    
                    
                    citizen.getEmail(),
                    citizen.getAddress(),
                    citizen.getPhone(),
                    citizen.getAmount_due(),
                    
                    citizen.getCredit_limit(),
                    citizen.getCredit_balance(),
                    citizen.getAccount_due_date());

            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                citizen.setUser_id(set.getString(1));
            }


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

    public static Citizen getCitizen(String columnToSearch, String value) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        Citizen cit = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM citizens ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
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
                
                cit.setEmail(res.getString("EMAIL"));
                cit.setAddress(res.getString("ADDRESS"));
                cit.setPhone(res.getString("PHONE"));
                cit.setAmount_due(res.getDouble("AMOUNT_DUE"));
                
                cit.setCredit_limit(res.getDouble("CREDIT_LIMIT"));
                cit.setCredit_balance(res.getDouble("CREDIT_BALANCE"));
                cit.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));

            }
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return cit;

    }

    public static List<Citizen> getAllCitizens() {
        List<Citizen> citizens = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String getCitizens = "SELECT * FROM citizens";
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(getCitizens);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next() == true) {
                Citizen cit = new Citizen();
                cit.setAmka(res.getString("AMKA"));
                cit.setVat(res.getString("VAT"));
                cit.setFirst_name(res.getString("FIRST_NAME"));
                cit.setLast_name(res.getString("LAST_NAME"));
                cit.setBirth_date(res.getString("BIRTH_DATE"));
                cit.setGender(res.getString("GENDER"));

                cit.setEmail(res.getString("EMAIL"));
                cit.setAddress(res.getString("ADDRESS"));
                cit.setPhone(res.getString("PHONE"));
                cit.setAmount_due(res.getDouble("AMOUNT_DUE"));

                cit.setCredit_limit(res.getDouble("CREDIT_LIMIT"));
                cit.setCredit_balance(res.getDouble("CREDIT_BALANCE"));
                cit.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));
                citizens.add(cit);
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return citizens;

    }

    public static void updateCitizen(Citizen cit) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            String sql = "UPDATE citizens "
                    + "SET EMAIL = ?, "
                    + "ADDRESS = ?,"
                    + " PHONE = ?,"
                    + " AMOUNT_DUE = ?,"
                    + " CREDIT_LIMIT = ?,"
                    + " CREDIT_BALANCE = ?,"
                    + " ACCOUNT_DUE_DATE = ? "
                    + "WHERE USERID = ?;";


            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql);
            UtilitiesDB.setValues(preparedStatement,
                    cit.getEmail(), cit.getAddress(), cit.getPhone(), cit.getAmount_due(), 
                    cit.getCredit_limit(), cit.getCredit_balance(), cit.getAccount_due_date(), 
                    cit.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

    }
    
    public static List<String> getGoodCitizens() {
        List<String> citizens = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            String sql_getmer = "SELECT FIRST_NAME, LAST_NAME FROM citizens"
                    + "WHERE AMOUNT_DUE = 0;";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            citizens = new ArrayList<>();
            while (res.next() == true) {
                String cit = res.getString("FIRST_NAME")+" "+ res.getString("LAST_NAME");
                citizens.add(cit);
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return citizens;

    }
    
    public static HashMap<String, Double> getBadCitizens() {
        HashMap<String, Double> citizens = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            String sql_getmer = "SELECT FIRST_NAME, LAST_NAME FROM citizens"
                    + "WHERE AMOUNT_DUE > 0"
                    + "ORDER BY AMOUNT_DUE;";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            citizens = new HashMap<String, Double>();
            while (res.next() == true) {
                String cit = res.getString("FIRST_NAME")+" "+ res.getString("LAST_NAME");
                double am = Double.valueOf(res.getString("AMOUNT_DUE"));
                citizens.put(cit, am);
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenDB.class.getName());
        }

        return citizens;

    }

}