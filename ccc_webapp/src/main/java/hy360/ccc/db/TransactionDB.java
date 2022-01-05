/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class TransactionDB {
    
    private static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException{
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
    
    public static void addTransaction(Transaction transaction){
        try{
            transaction.checkFields();
        }catch(Exception ex){
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            String insert_sql = "INSERT INTO transaction (TRANSACTION_TYPE, AMOUNT, PAY_TYPE, DATE) "+
                    "VALUES (?, ?, ?, ?)";
            setValues(preparedStatement, transaction.getTransaction_type(),
                    transaction.getAmount(), transaction.getPay_type(),
                    transaction.getDate());
            
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if(res.next()){
                int transaction_id = res.getInt(1);
                transaction.setTransaction_id(String.valueOf(transaction_id));
            }
            preparedStatement = con.prepareStatement("INSERT INTO transaction (TRANSACTION_ID) VALUES (?)");
            setValues(preparedStatement, transaction.getTransaction_id());
            preparedStatement.executeUpdate();
            
            ListIterator<String> it = transaction.getProducts().listIterator();
            while(it.hasNext()){
                insert_sql = "INSERT INTO BOUGHT_PRODUCT (TRANSACTION_ID, BOUGHT_PRODUCT_ID) "+
                    "VALUES (?, ?)";
                preparedStatement = con.prepareStatement(insert_sql);
                setValues(preparedStatement, transaction.getTransaction_id( ), it.next());
                preparedStatement.executeUpdate();         
            }

            
            
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
        
    }
    
}
