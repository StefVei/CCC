/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.MerchantPhone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class MerchantPhoneDB {
    
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
    /**
     * 
     * @param merchant_phones comma separated string eg "merchant_id, phone1, phone2"
     */
    public static void addCustomerPhones(String merchant_phones){
        
        List<String> phones = Arrays.asList(merchant_phones.split(",[ ]*"));
        String merchant_id = phones.get(0);
        phones.remove(0);
        
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            ListIterator<String> it = phones.listIterator();
            while(it.hasNext()){
                preparedStatement = con.prepareStatement("INSERT INTO merchant_phone"+
                    " MERCHANT_ID, MERCHANT_PHONE ) "+"VALUES (?, ?)");
                setValues(preparedStatement, merchant_id, it.next());
                preparedStatement.executeUpdate();
            }
        }catch(Exception ex){
            Logger.getLogger(MerchantPhoneDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    
        
        
        
        
    }
    
    /**
     * 
     * @param phone the phone number that was added 
     */
    public static void addMerchantPhone(MerchantPhone phone){
            
        try{
            phone.checkFields();
        }catch(Exception ex){
            Logger.getLogger(CustomerPhoneDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO merchant_phone"+
                    " MERCHANT_ID, MERCHANT_PHONE ) "+"VALUES (?, ?)");
            setValues(preparedStatement, phone.getMerchant_id(), phone.getPhone());
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(MerchantPhoneDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    /**
     * 
     * 
     * @param merchant_id
     * @return a list of all the phone_numbers in the current customer 
     */
    public static List<String> getMerchantPhones(int merchant_id){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<String> phones = new ArrayList<>();
        try{
            con = CccDB.getConnection();
            String sql_select = "SELECT * FROM customer_phone WHERE CUSTOMER_ID = ?";
            preparedStatement = con.prepareStatement(sql_select);
            setValues(preparedStatement, merchant_id);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            while(res.next() == true){
                String phone;
                phone = res.getString("PHONE_NUMBER");
                phones.add(phone);
            }
        }catch(Exception ex){
            Logger.getLogger(MerchantPhoneDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }

        return phones;
                
    }
    
}
