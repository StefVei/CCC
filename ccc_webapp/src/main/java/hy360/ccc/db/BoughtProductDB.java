/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.BoughtProduct;
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
public class BoughtProductDB {
    
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
     * @param prod the product that was bought 
     */
    public static void addBoughtProduct(BoughtProduct prod){
            
        try{
            prod.checkFields();
        }catch(Exception ex){
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO bought_product"+
                    " TRANSACTION_ID, BOUGHT_PRODUCT_ID ) "+"VALUES (?, ?)");
            setValues(preparedStatement, prod.getTransaction_id(), prod.getBought_product_id());
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }
    }
    
    /**
     * 
     * 
     * @param transaction_id
     * @return a list of all the products in the current transaction 
     */
    public static List<BoughtProduct> getBoughtProduct(int transaction_id){
        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<BoughtProduct> products = new ArrayList<>();
        try{
            con = CccDB.getConnection();
            String sql_select = "SELECT * FROM BOUGHT_PRODUCT WHERE TRANSACTION_ID = ?";
            preparedStatement = con.prepareStatement(sql_select);
            setValues(preparedStatement, transaction_id);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            while(res.next() == true){
                BoughtProduct prod = new BoughtProduct();
                prod.setBought_product_id(res.getString("BOUGHT_PRODUCT_ID"));
                prod.setTransaction_id(res.getString("TRANSACTION_ID"));
                products.add(prod);
            }
        }catch(Exception ex){
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(preparedStatement, con);
        }

        return products;
                
    }
    
    
}
