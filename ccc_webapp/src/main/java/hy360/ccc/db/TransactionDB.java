/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class TransactionDB {
    
    
    public static void addTransaction(Transaction transaction){
        try{
            transaction.checkFields();
        }catch(Exception ex){
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            String fields = transaction.getActiveFields();
            con = CccDB.getConnection();
            String values = fields.contains("CITIZEN_USERID") ? "?, ?, ?, ?, ? ,?"
                    : "?, ?, ?, ?, ?, ?, ?";
            String insert_sql = "INSERT INTO transaction (" + fields + ") VALUES (" + values + ")";

            preparedStatement = con.prepareStatement(insert_sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            if (fields.contains("CITIZEN_USERID")) {
                UtilitiesDB.setValues(preparedStatement, transaction.getPay_type(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getCitizen_id(),
                        transaction.getMerchant_cit_id());
            } else {
                UtilitiesDB.setValues(preparedStatement, transaction.getPay_type(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getEmployee_id(),
                        transaction.getCompany_id(),
                        transaction.getMerchant_comp_id());

            }
            
            preparedStatement.executeUpdate();
            

            
            
        }catch(Exception ex){
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }
        
    }

    public void completeTransaction(Transaction transaction) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            String fields = transaction.getActiveFields();
            con = CccDB.getConnection();
            String quer_part = fields.contains("CITIZEN_USERID") ? "SET PAY_TYPE = ?, "
                    + "SET TRANSACTION_TYPE = ?, SET AMOUNT = ?, SET DATE = ?, SET CITIZEN_USERID = ? "
                    + ", SET MERCHANT_TRADE = ?"
                    : "SET PAY_TYPE = ?, "
                    + "SET TRANSACTION_TYPE = ?, SET AMOUNT = ?, SET DATE = ?, SET EMPLOYEE_ID = ? "
                    + ", SET COMPANY_USERID = ?, MERCHANT_TRAFFIC";
            String insert_sql = "UPDATE transactions" + quer_part
                    + "TRANSACTION_ID = ?";

            preparedStatement = con.prepareStatement(insert_sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            if (fields.contains("CITIZEN_USERID")) {
                UtilitiesDB.setValues(preparedStatement, transaction.getPay_type(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getCitizen_id(),
                        transaction.getMerchant_cit_id(), transaction.getTransaction_id());
            } else {
                UtilitiesDB.setValues(preparedStatement, transaction.getPay_type(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getEmployee_id(),
                        transaction.getCompany_id(),
                        transaction.getMerchant_comp_id(), transaction.getTransaction_id());

            }

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(MerchantDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }

    }
    
}
