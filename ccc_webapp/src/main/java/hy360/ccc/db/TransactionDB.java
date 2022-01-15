/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                UtilitiesDB.setValues(preparedStatement, transaction.getPending(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getCitizen_id(),
                        transaction.getMerchant_cit_id());
            } else {
                UtilitiesDB.setValues(preparedStatement, transaction.getPending(),
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

    public static void updateTransaction(Transaction transaction) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            String fields = transaction.getActiveFields();
            con = CccDB.getConnection();
            String quer_part = fields.contains("CITIZEN_USERID") ? "SET PENDING = ?, "
                    + "SET TRANSACTION_TYPE = ?, SET AMOUNT = ?, SET DATE = ?, SET CITIZEN_USERID = ? "
                    + ", SET MERCHANT_TRADE = ?"
                    : "SET PAY_TYPE = ?, "
                    + "SET TRANSACTION_TYPE = ?, SET AMOUNT = ?, SET DATE = ?, SET transaction_ID = ? "
                    + ", SET COMPANY_USERID = ?, MERCHANT_TRAFFIC";
            String insert_sql = "UPDATE transactions" + quer_part
                    + "TRANSACTION_ID = ?";

            preparedStatement = con.prepareStatement(insert_sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            if (fields.contains("CITIZEN_USERID")) {
                UtilitiesDB.setValues(preparedStatement, transaction.getPending(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getCitizen_id(),
                        transaction.getMerchant_cit_id(), transaction.getTransaction_id());
            } else {
                UtilitiesDB.setValues(preparedStatement, transaction.getPending(),
                        transaction.getTransaction_type(), transaction.getAmount(),
                        transaction.getDate(), transaction.getTransaction_id(),
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

    public static Transaction getTransactions(String query_part, String query_type) {
        List<Transaction> transactions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sqlGetTransactions;
            if (query_type == "timeline") {
                ArrayList<String> query_dates = new ArrayList<>(Arrays.asList(query_part.split(",[ ]*")));
                sqlGetTransactions = "SELECT * FROM transactions T"
                        + "WHERE T.date >= " + query_dates.get(0)
                        + " AND T.date <= " + query_dates.get(1);

            } else {
                sqlGetTransactions = "SELECT * FROM transactions T"
                        + "WHERE transaction_ID in (" + query_part + ")";
            }
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(sqlGetTransactions);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next() == true) {
                Transaction transaction = new Transaction();
                transaction.setTransaction_id(res.getString("TRANSCTION_ID"));
                transaction.setDate(res.getString("DATE"));
                transaction.setPending(res.getString("PAY_TYPE"));
                transaction.setAmount(res.getString("AMOUNT"));
                transaction.setTransaction_type(res.getString("TRANSACTION_TYPE"));
                transaction.setCitizen_id(res.getString("CITIZEN_USERID"));
                transaction.setCompany_id(res.getString("COMPANY_USERID"));
                transaction.setMerchant_cit_id(res.getString("MERCHANT_TRADE"));
                transaction.setMerchant_comp_id(res.getString("MERCHANT_TRAFFIC"));
                transaction.setEmployee_id(res.getString("EMPLOYEE_ID"));

                transactions.add(transaction);
            }

        } catch (Exception ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }

        return null;
    }
    
}
