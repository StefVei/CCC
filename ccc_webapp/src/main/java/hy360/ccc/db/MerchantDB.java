/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Merchant;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class MerchantDB {
    
    
    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException{
        for(int i = 0; i < values.length; i++){
            preparedStatement.setObject(i + 1, values[i]);
        }
    }
    
        
    private static void closeConnection(PreparedStatement preparedStatement, Connection con){
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            }catch(SQLException sql_ex){
                Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, sql_ex);
            }
        }
        if(con != null){
            try{
                con.close();
            }catch(SQLException sql_ex){
                Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, sql_ex);
            }
        }
    }
    
    
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
            
            setValues(preparedStatement,
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
            closeConnection(preparedStatement, con);
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
            closeConnection(preparedStatement, con);
        }
    }}
