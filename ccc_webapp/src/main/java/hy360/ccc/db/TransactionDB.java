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

            
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO transactions "
                    + "( `PENDING`, `TRANSACTION_TYPE`, `AMOUNT`, `DATE` )"
                    + " VALUES (? ,? ,? ,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            UtilitiesDB.setValues(preparedStatement,
                    transaction.getPending(),
                    transaction.getTransaction_type(),
                    transaction.getAmount(),
                    transaction.getDate()
                    );
            
            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                transaction.setTransaction_id(set.getString(1));
            }
            
            
        }catch(Exception ex){
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }
        
    }

    public static void updateTransaction(Transaction transaction) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            String mer_sql = "UPDATE transactions "
                    + "SET PENDING = ?, "
                    + " TRANSACTION_TYPE = ?, "
                    + " AMOUNT = ?, "
                    + " DATE = ?, "
                    + "WHERE TRANSACTION_ID = ? ;";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(mer_sql);
            UtilitiesDB.setValues(preparedStatement, transaction.getPending(),
                    transaction.getTransaction_type(), transaction.getAmount(), transaction.getDate());
            
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }

    }

    public static Transaction getTransaction(String transaction) {
        Transaction tr = new Transaction();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getmer = "SELECT * FROM transactions WHERE TRANSACTION_ID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, Integer.valueOf(transaction));
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setPending(res.getString("PENDING"));
                tr.setTransaction_type(res.getString("TRANSACTION_TYPE"));
                tr.setAmount(res.getString("AMOUNT"));
                tr.setDate(res.getString("DATE"));
            }

        } catch (Exception ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }

        return tr;
    }

    public static List<Transaction> getTransactionsByDates(String date_less, String date_greater) {
        List<Transaction> transactions = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            String sql_getmer = "SELECT * FROM transactions T "
                    + "WHERE T.DATE >= ? AND T.DATE <= ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getmer);
            UtilitiesDB.setValues(preparedStatement, date_less, date_greater);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            transactions = new ArrayList<>();
            while (res.next() == true) {
                Transaction tr = new Transaction();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setPending(res.getString("PENDING"));
                tr.setTransaction_type(res.getString("TRANSACTION_TYPE"));
                tr.setAmount(res.getString("AMOUNT"));
                tr.setDate(res.getString("DATE"));
                transactions.add(tr);
            }

        } catch (Exception ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, TransactionDB.class.getName());
        }

        return transactions;

    }

    
}
