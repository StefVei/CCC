/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.CustomerPhone;
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
public class CustomerPhoneDB {
    
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
     * @param phone the phone number that was added 
     */
    public static void addCustomerPhone(CustomerPhone phone){
            
        try{
            phone.checkFields();
        }catch(Exception ex){
            Logger.getLogger(CustomerPhoneDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO customer_phone"+
                    " CUSTOMER_ID, CUSTOMER_PHONE ) "+"VALUES (?, ?)");
            setValues(preparedStatement, phone.getCustomer_id(), phone.getPhone());
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(CustomerPhoneDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    /**
     * 
     * 
     * @param customer_id
     * @return a list of all the phone_numbers in the current customer 
     */
    public static List<CustomerPhone> getCustomerPhone(int customer_id){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<CustomerPhone> phones = new ArrayList<>();
        try{
            con = CccDB.getConnection();
            String sql_select = "SELECT * FROM customer_phone WHERE CUSTOMER_ID = ?";
            preparedStatement = con.prepareStatement(sql_select);
            setValues(preparedStatement, customer_id);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            while(res.next() == true){
                CustomerPhone phone = new CustomerPhone();
                phone.setPhone(res.getString("PHONE_NUMBER"));
                phone.setCustomer_id(res.getString("CUSTOMER_ID"));
                phones.add(phone);
            }
        }catch(Exception ex){
            Logger.getLogger(CustomerPhoneDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }

        return phones;
                
    }

    
    
    
    
    
    
    
    
    
    
}
