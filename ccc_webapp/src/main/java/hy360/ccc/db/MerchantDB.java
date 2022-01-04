/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Merchant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
            merchant.checkFields();
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO merchant (USERNAME, "+ 
                    "PASSWORD, EMAIL, ADDRESS, AMOUNT_DUE, FIRST_NAME, LAST_NAME,"+
                    " SYPPLY, GAIN) "+"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            setValues(preparedStatement,
                    merchant.getUserName(),
                    merchant.getPassword(),
                    merchant.getEmail(),
                    merchant.getAddress(),
                    merchant.getAmount_due(),
                    merchant.getFirst_name(),
                    merchant.getLast_name(),
                    merchant.getSupply(),
                    merchant.getGain());
            
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                int merchant_id = res.getInt(1);
                merchant.setMerchant_id(String.valueOf(merchant_id));
            }
            preparedStatement = con.prepareStatement("INSERT INTO merchant (MERCHANT_ID) VALUES (?)");
            setValues(preparedStatement, merchant.getMerchant_id());
            preparedStatement.executeUpdate();

            
            
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    
    }
    
    
        public static void deleteMerchant(Merchant merchant){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            String del = "DELETE FROM merchant WHERE MERCHANT_ID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(merchant.getMerchant_id()));
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    
    public static void updateMerchant(Merchant merchant){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            String updatemerchant_sql = "UPDATE merchant "
                    + "SET USERNAME = ? " 
                    +"SET PASSWORD = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + "SET AMOUNT_DUE = ? "
                    + "SET FIRST_NAME = ? "
                    + "SET LAST_NAME = ? "
                    +" SET SUPPLY = ? "
                    + "SET GAIN = ? "
                    + "WHERE MERCHANT_ID = ?";
            
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updatemerchant_sql);
            setValues(preparedStatement, merchant.getUserName(),
                    merchant.getPassword(), merchant.getEmail(),
                    merchant.getAddress(), merchant.getAmount_due(),
                    merchant.getFirst_name(), merchant.getLast_name(),
                    merchant.getSupply(), merchant.getGain(),
                    merchant.getMerchant_id());
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    public static List<Merchant> getMerchants(){
        List<Merchant> merchants = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            String sql_getMerchants = "SELECT * FROM MERCHANT";
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(sql_getMerchants);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            
            while(res.next() == true){
                Merchant mer = new Merchant();
                mer.setUserName(res.getString("USERNAME"));
                mer.setEmail(res.getString("EMAIL"));
                mer.setPhone(res.getString("PHONE"));
                mer.setAddress(res.getString("ADDRESS"));
                mer.setAccount_number(res.getString("ACCOUNT_NUMBER"));
                mer.setAmount_due(res.getString("AMOUNT_DUE"));
                mer.setPassword(res.getString("PASSWORD"));
                mer.setFirst_name(res.getString("FIRST_NAME"));
                mer.setLast_name(res.getString("LAST_NAME"));
                mer.setGain(res.getString("GAIN"));
                mer.setSupply(res.getString("SUPPLY"));
                mer.setMerchant_id(res.getString("MERCHANT_ID"));
                merchants.add(mer);
            }
                   
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }

        return merchants;
        
    }
 
    
    
    
}
