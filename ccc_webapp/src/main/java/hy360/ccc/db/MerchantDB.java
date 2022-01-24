/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Merchant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class MerchantDB {
    
    
    public static void addMerchant(Merchant merchant){
        try{
            //merchant.checkFields();
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO merchants "
                    + "(`USERID`, `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `SUPPLY`, `GAIN`, "
                    + " `PURCHASES_TOTAL`, `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`)"
                    + " VALUES (? ,? ,? ,? ,? ,? , ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            UtilitiesDB.setValues(preparedStatement,
                    merchant.getUser_id(),
                    merchant.getFirst_name(),
                    merchant.getLast_name(),
                    merchant.getBirth_date(),
                    merchant.getGender(),
                    merchant.getSupply(),
                    merchant.getGain(),
                    merchant.getPurchases_total(),
                    merchant.getEmail(),
                    merchant.getAddress(),
                    merchant.getPhone(),                    
                    merchant.getAmount_due());

            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();

            if (res.next()) {
                merchant.setUser_id(res.getString(1));
            }
        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }
    
    }


    public static void updateMerchant(Merchant merchant) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String mer_sql = "UPDATE merchants "
                    + "SET FIRST_NAME = ?, "
                    + "LAST_NAME = ?, "
                    + "BIRTH_DATE = ?, "
                    + "GENDER = ?, "
                    + "SUPPLY = ?, "
                    + "GAIN = ?, "
                    + "PURCHASES_TOTAL = ?, "
                    + "EMAIL = ?, "
                    + "ADDRESS = ?, "
                    + "PHONE = ?, "
                    + "AMOUNT_DUE = ?"
                    + "WHERE USERID = ? ;";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(mer_sql);
            UtilitiesDB.setValues(preparedStatement, merchant.getFirst_name(),
                    merchant.getLast_name(), merchant.getBirth_date(), merchant.getGender(),
                    merchant.getSupply(), merchant.getGain(), merchant.getPurchases_total(), 
                    merchant.getEmail(), merchant.getAddress(), merchant.getPhone(), 
                    merchant.getAmount_due(), merchant.getUser_id());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }
    }

    /**
     *
     * @param columnToSearch column of SQL TO search USERID OR USERNAME
     * @param value the value of the column to be searched
     * @return
     */
    public static Merchant getMerchant(String columnToSearch, String value) {

        Merchant mer = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM merchants ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                mer = new Merchant();
                mer.setUser_id(res.getString("USERID"));
                mer.setFirst_name(res.getString("FIRST_NAME"));
                mer.setLast_name(res.getString("LAST_NAME"));
                mer.setBirth_date(res.getString("BIRTH_DATE"));
                mer.setGender(res.getString("GENDER"));
                mer.setSupply(res.getDouble("SUPPLY"));
                mer.setGain(res.getDouble("GAIN"));
                mer.setPurchases_total(res.getInt("PURCHASES_TOTAL"));
                mer.setEmail(res.getString("EMAIL"));
                mer.setAddress(res.getString("ADDRESS"));
                mer.setPhone(res.getString("PHONE"));
                mer.setAmount_due(res.getDouble("AMOUNT_DUE"));
                mer.setUser_id(res.getString("USERID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }

        return mer;

    }
    
    public static List<Merchant> getGoodMerchants() {
        List<Merchant> merchants = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            String sql_getmer = "SELECT * FROM merchants"
                    + " WHERE AMOUNT_DUE = ? ;";
            con = CccDB.getConnection();


            preparedStatement = con.prepareStatement(sql_getmer);

            UtilitiesDB.setValues(preparedStatement, 0.0);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            merchants = new ArrayList<>();
            while (res.next() == true) {
                Merchant mer = new Merchant();
                mer.setUser_id(res.getString("USERID"));
                mer.setFirst_name(res.getString("FIRST_NAME"));
                mer.setLast_name(res.getString("LAST_NAME"));
                mer.setBirth_date(res.getString("BIRTH_DATE"));
                mer.setGender(res.getString("GENDER"));
                mer.setSupply(res.getDouble("SUPPLY"));
                mer.setGain(res.getDouble("GAIN"));
                mer.setPurchases_total(res.getInt("PURCHASES_TOTAL"));
                mer.setEmail(res.getString("EMAIL"));
                mer.setAddress(res.getString("ADDRESS"));
                mer.setPhone(res.getString("PHONE"));
                mer.setAmount_due(res.getDouble("AMOUNT_DUE"));
                mer.setUser_id(res.getString("USERID"));

                merchants.add(mer);
            }

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }

        return merchants;

    }


    public static void setMerchantOfTheMonth() {
        Merchant mer = new Merchant();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement("SET GLOBAL event_scheduler = 1; ");
            preparedStatement.execute();

            String mer_event = "CREATE EVENT IF NOT EXISTS merchant_of_the_month ON SCHEDULE EVERY '1' MONTH STARTS '2022-1-24 18:03:00' DO UPDATE merchants SET AMOUNT_DUE = AMOUNT_DUE * 0.95 WHERE PURCHASES_TOTAL >= ( SELECT MAX(PURCHASES_TOTAL) FROM merchants )";

            preparedStatement = con.prepareStatement(mer_event);
            preparedStatement.execute();



        } catch (SQLException ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Merchant> getBadMerchants() {
        List<Merchant> merchants = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            String sql_getmer = "SELECT * FROM merchants"
                    + " WHERE AMOUNT_DUE > ? ;";
            con = CccDB.getConnection();


            preparedStatement = con.prepareStatement(sql_getmer);

            UtilitiesDB.setValues(preparedStatement, 0.0);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            merchants = new ArrayList<>();
            while (res.next() == true) {
                Merchant mer = new Merchant();
                mer.setUser_id(res.getString("USERID"));
                mer.setFirst_name(res.getString("FIRST_NAME"));
                mer.setLast_name(res.getString("LAST_NAME"));
                mer.setBirth_date(res.getString("BIRTH_DATE"));
                mer.setGender(res.getString("GENDER"));
                mer.setSupply(res.getDouble("SUPPLY"));
                mer.setGain(res.getDouble("GAIN"));
                mer.setPurchases_total(res.getInt("PURCHASES_TOTAL"));
                mer.setEmail(res.getString("EMAIL"));
                mer.setAddress(res.getString("ADDRESS"));
                mer.setPhone(res.getString("PHONE"));
                mer.setAmount_due(res.getDouble("AMOUNT_DUE"));
                mer.setUser_id(res.getString("USERID"));

                merchants.add(mer);
            }

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }

        return merchants;

    }
}
