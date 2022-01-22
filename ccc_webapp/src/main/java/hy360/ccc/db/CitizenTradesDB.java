/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.CM_Trades;
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
public class CitizenTradesDB {

    public static void addTrade(String citizen_id, String merchant_id, String transaction_id) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO cm_trades "
                    + "( `CITIZEN_USERID`, `MERCHANT_USERID`, `TRANSACTION_ID` )"
                    + " VALUES (? ,? ,?)");

            UtilitiesDB.setValues(preparedStatement,
                    citizen_id,
                    merchant_id,
                    transaction_id);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(TransactionDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenTradesDB.class.getName());
        }

    }

    /**
     *
     * @param columnToSearch column of sql table
     * @param value value of the column to be searched
     * @return
     */
    public static List<CM_Trades> getTrades(String columnToSearch, String value) {

        List<CM_Trades> trades = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM cm_trades ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next()) {
                CM_Trades tr = new CM_Trades();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setCitizen_id(res.getString("CITIZEN_USERID"));
                tr.setMerchant_id(res.getString("MERCHANT_USERID"));
                trades.add(tr);
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenTradesDB.class.getName());
        }

        return trades;
    }

    public static CM_Trades getTrade(String transaction_id) {

        CM_Trades tr = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;

    
        try {
            StringBuilder insQuery = new StringBuilder();
            con = CccDB.getConnection();
            insQuery.append("SELECT * FROM cm_trades ")
                    .append(" WHERE TRANSACTION_ID = ")
                    .append("'").append(transaction_id).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next()) {
                tr = new CM_Trades();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setCitizen_id(res.getString("CITIZEN_USERID"));
                tr.setMerchant_id(res.getString("MERCHANT_USERID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(CitizenTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenTradesDB.class.getName());
        }

        return tr;

    }

    public static void deleteTrade(String transaction_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM cm_trades WHERE TRANSACTION_ID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(transaction_id));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenTradesDB.class.getName());
        }
    }

    /**
     *
     * @param column CITIZEN_USERID OR MERCHANT_USERID
     * @param value the id
     */
    public static void deleteTrades(String column, String value) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            StringBuilder insQuery = new StringBuilder();

            insQuery.append("DELETE FROM cm_trades ")
                    .append(" WHERE").append(" ").append(column).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(CitizenTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CitizenTradesDB.class.getName());
        }
    }

}
