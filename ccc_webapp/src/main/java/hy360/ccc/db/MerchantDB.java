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
                    + "( `FIRST_NAME`, `LAST_NAME`, `BIRTH_DATE`, `GENDER`, `SUPPLY`, `GAIN`, "
                    + " `PURCHASES_TOTAL`, `USERNAME`, `PASSWORD`, `PHONE`, `EMAIL`, `ADDRESS`, `AMOUNT_DUE`,"
                    + " `ACCOUNT_NUMBER` )"
                    + " VALUES (? ,? ,? ,? ,? ,? , ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            UtilitiesDB.setValues(preparedStatement,
                    merchant.getFirst_name(),
                    merchant.getLast_name(),
                    merchant.getBirth_date(),
                    merchant.getGender(),
                    merchant.getSupply(),
                    merchant.getGain(),
                    merchant.getPurchases_total(),
                    merchant.getUserName(),
                    merchant.getPassword(),
                    merchant.getPhone(),
                    merchant.getEmail(),
                    merchant.getAddress(),
                    merchant.getAmount_due(),
                    merchant.getAccount_number());
            
        preparedStatement.executeUpdate();
        
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }
    
    }
    
    public static void deleteMerchant(Merchant merchant) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            String del = "DELETE FROM merchants WHERE USERID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(merchant.getUser_id()));
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }
    }

    public static void updateMerchant(Merchant merchant) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String mer_sql = "UPDATE merchants "
                    + "SET FIRST_NAME = ? "
                    + "SET LAST_NAME = ? "
                    + "SET BIRTH_DATE = ? "
                    + "SET PHONE = ? "
                    + "SET SUPPLY = ? "
                    + "SET GAIN = ? "
                    + "SET PURCHASES_TOTAL = ? "
                    + "SET GENDER = ? "
                    + "SET USERNAME = ? "
                    + "SET PASSWORD = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + "SET AMOUNT_DUE = ?"
                    + "WHERE USERID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(mer_sql);
            UtilitiesDB.setValues(preparedStatement, merchant.getFirst_name(),
                    merchant.getLast_name(), merchant.getBirth_date(),
                    merchant.getPhone(), merchant.getSupply(), merchant.getGain(),
                    merchant.getPurchases_total(), merchant.getGender(),
                    merchant.getUserName(), merchant.getPassword(), merchant.getEmail(),
                    merchant.getAddress(), merchant.getAmount_due(), merchant.getUser_id());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }
    }

    public static Merchant getMerchant(int merchant_id) {

        Merchant mer = new Merchant();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "SELECT * FROM merchants WHERE USERID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, merchant_id);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                mer.setUser_id(res.getString("USERID"));
                mer.setFirst_name(res.getString("FIRST_NAME"));
                mer.setLast_name(res.getString("LAST_NAME"));
                mer.setBirth_date(res.getString("BIRTH_DATE"));
                mer.setGender(res.getString("GENDER"));
                mer.setSupply(res.getString("SUPPLY"));
                mer.setGain(res.getString("GAIN"));
                mer.setPurchases_total(res.getString("PURCHASES_TOTAL"));
                mer.setPhone(res.getString("PHONE"));
                mer.setEmail(res.getString("EMAIL"));
                mer.setUserName(res.getString("USERNAME"));
                mer.setPassword(res.getString("PASSWORD"));
                mer.setAddress(res.getString("ADDRESS"));
                mer.setAmount_due(res.getString("AMOUNT_DUE"));
                mer.setAccount_number(res.getString("ACCOUNT_NUMBER"));

                mer.setUser_id(res.getString("USERID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, MerchantDB.class.getName());
        }

        return mer;

    }

}
