/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.CM_Traffics;
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
public class CompanyTradesDB {



/**
 *
 * @author panagiotisk
 */
    /**
     *
     * @param em_id
     * @param comp_id
     * @param merchant_id
     * @param transaction_id
     */
    public static void addTrade(String transaction_id, String merchant_id, String comp_id, String em_id) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO cm_traffics "
                    + "( `EMPLOYEE_ID`, `COMPANY_USERID`, `MERCHANT_USERID` ,`TRANSACTION_ID` )"
                    + " VALUES (? ,? ,?, ?)");

            UtilitiesDB.setValues(preparedStatement, em_id,
                    comp_id,
                    merchant_id,
                    transaction_id);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()
            ).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }

    }


    /**
     *
     * @param columnToSearch column of sql table
     * @param value value of the column to be searched
     * @return
     */
    public static List<CM_Traffics> getTrades(String columnToSearch, String value) {

        List<CM_Traffics> trades = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            StringBuilder insQuery = new StringBuilder();
            con = CccDB.getConnection();


            insQuery.append("SELECT * FROM cm_traffics ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next()) {
                CM_Traffics tr = new CM_Traffics();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setCompany_id(res.getString("COMPANY_USERID"));
                tr.setMerchant_id(res.getString("MERCHANT_USERID"));
                tr.setEmployee_id(res.getString("EMPLOYEE_ID"));
                trades.add(tr);
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }

        return trades;
    }

    /**
     *
     * @param employee_ids comma separated ids : "id1, id2, id3, id4"
     * @return
     */
    public static List<CM_Traffics> getTradesByEmployees(String employeesId) {
        List<CM_Traffics> trades = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String sql = "SELECT * FROM cm_traffics"
                    + "WHERE EMPLOYEE_ID in (" + employeesId + ")";

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next()) {
                CM_Traffics tr = new CM_Traffics();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setCompany_id(res.getString("CITIZEN_USERID"));
                tr.setMerchant_id(res.getString("MERCHANT_USERID"));
                tr.setEmployee_id(res.getString("EMPLOYEE_ID"));
                trades.add(tr);
            }


        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }

        return trades;
    }

    public static CM_Traffics getTrade(String transaction_id) {

        CM_Traffics tr = null;
        PreparedStatement preparedStatement = null;
        Connection con = null;


        try {
            StringBuilder insQuery = new StringBuilder();
            con = CccDB.getConnection();

            insQuery.append("SELECT * FROM cm_traffics ")
                    .append(" WHERE TRANSACTION_ID = ")
                    .append("'").append(transaction_id).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next()) {
                tr = new CM_Traffics();
                tr.setTransaction_id(res.getString("TRANSACTION_ID"));
                tr.setCompany_id(res.getString("COMPANY_USERID"));
                tr.setMerchant_id(res.getString("MERCHANT_USERID"));
                tr.setEmployee_id(res.getString("EMPLOYEE_ID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }

        return tr;

    }

    public static void deleteTrade(String transaction_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM cm_traffics WHERE TRANSACTION_ID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(transaction_id));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }
    }

    /**
     *
     * @param column COMPANY_USERID OR MERCHANT_USERID
     * @param value the id
     */
    public static void deleteTrades(String column, String value) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            StringBuilder insQuery = new StringBuilder();

            insQuery.append("DELETE FROM cm_traffics ")
                    .append(" WHERE").append(" ").append(column).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(CompanyTradesDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyTradesDB.class.getName());
        }
    }

}







