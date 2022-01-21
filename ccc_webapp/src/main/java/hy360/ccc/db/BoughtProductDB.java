/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.BoughtProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class BoughtProductDB {
    
    
    /**
     * 
     * @param product_id "product_id"
     */
    public static void addBoughtProduct(String product_id, String tr_id, String mer_id, double totalQuantity) {
            
        String transaction_id = tr_id;
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO bought_products "
                    + "( TRANSACTION_ID, PRODUCT_ID, MERCHANT_USERID, TOTAL ) "
                    + "VALUES (?, ?, ?, ?)");
            UtilitiesDB.setValues(preparedStatement, transaction_id, product_id,
                    mer_id, totalQuantity);
                preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, BoughtProductDB.class.getName());
        }
    }
    
    /**
     * 
     * 
     * @param transaction_id
     * @return a list of all the products in the current transaction 
     */
    public static List<BoughtProduct> getBoughtProducts(int transaction_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<BoughtProduct> products = new ArrayList<>();
        try{
            con = CccDB.getConnection();
            String sql_select = "SELECT * FROM BOUGHT_PRODUCT WHERE TRANSACTION_ID = ?";
            preparedStatement = con.prepareStatement(sql_select);
            UtilitiesDB.setValues(preparedStatement, transaction_id);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            while(res.next() == true){
                BoughtProduct prod = new BoughtProduct();
                prod.setProduct_id(res.getString("PRODUCT_ID"));
                prod.setTransaction_id(res.getString("TRANSACTION_ID"));
                prod.setMerchant_id(res.getString("MERCHANT_USERID"));
                prod.setTotal(res.getDouble("TOTAL"));
                products.add(prod);
            }
        }catch(Exception ex){
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, BoughtProductDB.class.getName());
        }

        return products;
                
    }

    /**
     *
     *
     * @param transaction_id
     * @return products in the current transaction
     */
    public static BoughtProduct getBoughtProduct(int transaction_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        BoughtProduct product = null;
        try {
            con = CccDB.getConnection();
            String sql_select = "SELECT * FROM BOUGHT_PRODUCT WHERE TRANSACTION_ID = ?";
            preparedStatement = con.prepareStatement(sql_select);
            UtilitiesDB.setValues(preparedStatement, transaction_id);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getResultSet();
            if (res.next() == true) {
                product = new BoughtProduct();
                product.setProduct_id(res.getString("PRODUCT_ID"));
                product.setTransaction_id(res.getString("TRANSACTION_ID"));
                product.setMerchant_id(res.getString("MERCHANT_USERID"));
                product.setTotal(res.getDouble("TOTAL"));
            }
        } catch (Exception ex) {
            Logger.getLogger(BoughtProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, BoughtProductDB.class.getName());
        }

        return product;

    }
    
    
}
