/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sckou
 */
public class ProductDB {
    
        
    
    public static void addProduct(Product product){
        try{
            //merchant.checkFields();
        }catch(Exception ex){
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
    
        try{
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO products "
                    + "( `NAME`, `PRICE`, `QUANTITY`, `MERCHANT_USERID` )"
                    + " VALUES (? ,? ,? ,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            UtilitiesDB.setValues(preparedStatement,
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getMerchant_id()
            );
            
        preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();

            if (res.next()) {
                product.setProduct_id(res.getString(1));
            }

        }catch(Exception ex){
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }
    
    }
    
    public static void deleteProduct(String product_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try{
            con = CccDB.getConnection();
            String del = "UPDATE products SET DELETED = ? WHERE PROUCT_ID = ?";
            preparedStatement = con.prepareStatement(del);
            UtilitiesDB.setValues(preparedStatement, true, product_id);
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE,null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }
    }

    public static void updateProduct(Product product) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String mer_sql = "UPDATE products "
                    + "SET NAME = ?, "
                    + "PRICE = ?, "
                    + "DELETED = ?, "
                    + "QUANTITY = ? "
                    + "WHERE PRODUCT_ID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(mer_sql);
            UtilitiesDB.setValues(preparedStatement, product.getName(),
                    product.getPrice(), product.getQuantity(), product.getProduct_id());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }
    }

    public static Product getProduct(String product) {

        Product pro = new Product();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "SELECT * FROM products WHERE PRODUCT_ID = ? ";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, Integer.valueOf(product));
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                pro.setProduct_id(res.getString("PRODUCT_ID"));
                pro.setName(res.getString("NAME"));
                pro.setPrice(res.getDouble("PRICE"));
                pro.setQuantity(res.getInt("QUANTITY"));
                pro.setMerchant_id(res.getString("MERCHANT_USERID"));
                pro.setDeleted(res.getBoolean("DELETED"));
            }

        } catch (Exception ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }

        return pro;

    }

    public static List<Product> getProducts(String merchant) {

        List<Product> products = new ArrayList<Product>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "SELECT * FROM products WHERE MERCHANT_USERID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, Integer.valueOf(merchant));
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next() == true) {
                Product pro = new Product();
                pro.setProduct_id(res.getString("PRODUCT_ID"));
                pro.setName(res.getString("NAME"));
                pro.setPrice(res.getDouble("PRICE"));
                pro.setQuantity(res.getInt("QUANTITY"));
                pro.setMerchant_id(res.getString("MERCHANT_USERID"));
                pro.setDeleted(res.getBoolean("DELETED"));

                products.add(pro);
            }

        } catch (Exception ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }

        return products;

    }

    public static void deleteProducts(String merchant_id) {
        List<Product> products = new ArrayList<Product>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "UPDATE products SET DELETED = 1 WHERE MERCHANT_USERID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, Integer.valueOf(merchant_id));
            preparedStatement.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }

    }

    /**
     *
     * @return all the NON deleted products in the db
     */
    public static List<Product> getAllNonDeletedProducts() {

        List<Product> products = new ArrayList<Product>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "SELECT * FROM products WHERE DELETED = ? ;";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, false);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next() == true) {
                Product pro = new Product();
                pro.setProduct_id(res.getString("PRODUCT_ID"));
                pro.setName(res.getString("NAME"));
                pro.setPrice(res.getDouble("PRICE"));
                pro.setQuantity(res.getInt("QUANTITY"));
                pro.setMerchant_id(res.getString("MERCHANT_USERID"));
                products.add(pro);
            }

        } catch (Exception ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, ProductDB.class.getName());
        }

        return products;

    }



}
