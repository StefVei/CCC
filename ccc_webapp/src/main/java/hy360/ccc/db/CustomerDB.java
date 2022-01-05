/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class CustomerDB {
    
    
    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException{
        for(int i = 0; i < values.length; i++){
            preparedStatement.setObject(i + 1, values[i]);
        }
    }
    
    public static void addCustomer(Customer customer){
        try{
            customer.checkFields();
        }catch(Exception ex){
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO customer (USERNAME, "+ 
                    "PASSWORD, EMAIL, ADDRESS, ACCOUNT_DUE_DATE,"+
                    " CREDIT_BALANCE, ACCOUNT_NUMBER ) "+"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            setValues(preparedStatement,
                    customer.getUserName(),
                    customer.getPassword(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getAccount_due_date(),
                    customer.getCredit_balance(),
                    customer.getAccount_number());
            
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                int customer_id = res.getInt(1);
                customer.setCustomer_id(String.valueOf(customer_id));
            }
            preparedStatement = con.prepareStatement("INSERT INTO customer (CUSTOMER_ID) VALUES (?)");
            setValues(preparedStatement, customer.getCustomer_id());
            preparedStatement.executeUpdate();

            
            
        }catch(Exception ex){
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    
    }
    
    public static Customer getCustomer(int customer_id){
        PreparedStatement preparedStatement = null;
        Connection con  = null;
        Customer customer = null;
        try{
            con = CccDB.getConnection();
            String sql = "SELECT USERNAME, PASSWORD, EMAIL, ADDRESS, AMOUNT_DUE, ACCOUNT_DUE_DATE, CREDIT_LIMIT,"+
                    " CREDIT_BALANCE, ACCOUNT_NUMBER "+"FROM customer WHERE "+
                    "ACCOUNT_NUMBER = (SELECT ACCOUNT_NUMBER FROM CUSTOMER WHERE CUSTOMER_ID = ? ";
            preparedStatement = con.prepareStatement(sql);
            setValues(preparedStatement, customer_id);
            ResultSet res = preparedStatement.executeQuery();
            customer = new Customer();
            if(res.next() == true){
                customer.setUserName(res.getString("USERNAME"));
                customer.setPassword(res.getString("PASSWORD"));
                customer.setEmail(res.getString("EMAIL"));
                customer.setAddress(res.getString("ADDRESS"));
                customer.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));
                customer.setCredit_balance(res.getString("CREDIT_BALANCE"));
                customer.setAmount_due(res.getString("AMOUNT_DUE"));
                customer.setCredit_limit(res.getString("CREDIT_LIMIT"));
                customer.setAccount_number(res.getString("ACCOUNT_NUMBER"));
            }
                
                
        } catch (Exception ex) {
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
            
        return customer;
        
    }
    
    public static void updateCustomer(Customer customer){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            String updateCustomer_sql = "UPDATE customer "
                    + "SET USERNAME = ? " 
                    +"SET PASSWORD = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + "SET AMOUNT_DUE = ? "
                    + "SET ACCOUNT_DUE_DATE = ? "
                    + "SET CREDIT_LIMIT = ? "
                    +" SET CREDIT_BALANCE = ? "
                    + "SET ACCOUNT_NUMBER = ? "
                    + "WHERE CUSTOMER_ID = ?";
            
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updateCustomer_sql);
            setValues(preparedStatement, customer.getUserName(),
                    customer.getPassword(), customer.getEmail(),
                    customer.getAddress(), customer.getAmount_due(),
                    customer.getAccount_due_date(), customer.getCredit_limit(),
                    customer.getCredit_balance(), customer.getAccount_number(),
                    customer.getCustomer_id());
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    
    public static void deleteCustomer(Customer customer){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            String del = "DELETE FROM customer WHERE CUSTOMER_ID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(customer.getCustomer_id()));
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    
    private static void closeConnection(PreparedStatement preparedStatement, Connection con){
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            }catch(SQLException sql_ex){
                Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE,null, sql_ex);
            }
        }
        if(con != null){
            try{
                con.close();
            }catch(SQLException sql_ex){
                Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE,null, sql_ex);
            }
        }
    }
    
    
}
