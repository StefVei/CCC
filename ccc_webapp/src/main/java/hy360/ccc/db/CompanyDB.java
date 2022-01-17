/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class CompanyDB {

    public static void addCompany(Company company) {

        try {
            company.checkFields();
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            con = CccDB.getConnection();

            String generatedColumns[] = {"COMPANY_ID"};

            preparedStatement = con.prepareStatement("INSERT INTO companies "
                    + "(`NAME`, `ESTABLISHMENT_DATE`,"
                    + " `USERNAME`, `PASSWORD`, `PHONE`, `EMAIL`, `ADDRESS`, `AMOUNT_DUE`,"
                    + " `ACCOUNT_NUMBER`, `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE` )"
                    + " VALUES (?, ?,"
                    + "?, ?, ?, ?, ?, ?,"
                    + "?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            UtilitiesDB.setValues(preparedStatement,
                    company.getName(),
                    company.getEstablishment_date(),
                    company.getUserName(),
                    company.getPassword(),
                    company.getPhone(),
                    company.getEmail(),
                    company.getAddress(),
                    company.getAmount_due(),
                    company.getAccount_number(),
                    company.getCredit_limit(),
                    company.getCredit_balance(),
                    company.getAccount_number());

            preparedStatement.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }

    }

    public static void deleteCompany(Company company) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM companies WHERE USERID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(company.getUser_id()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }
    }

    /**
     *
     * @param columnToSearch for example "NAME" or "USERID" column must be
     * UNIQUE
     * @param value The value of the desired row
     * @return a Company with all the fields
     */
    public static Company getCompany(String columnToSearch, String value) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        Company comp = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM companies ")
                    .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                    .append("'").append(value).append("';");

            preparedStatement = con.prepareStatement(insQuery.toString());
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next()) {
                comp = new Company();
                comp.setUser_id(res.getString("USERID"));
                comp.setEstablishment_date(res.getString("ESTABLISHMENT_DATE"));
                //comp.setLogotype(res.getString("LOGOTYPE"));
                comp.setName(res.getString("NAME"));
                comp.setAddress(res.getString("ADDRESS"));
                comp.setUserName(res.getString("USERNAME"));
                comp.setPassword(res.getString("PASSWORD"));
                comp.setEmail(res.getString("EMAIL"));
                comp.setPhone(res.getString("PHONE"));
                comp.setCredit_limit(res.getString("CREDIT_LIMIT"));
                comp.setCredit_balance(res.getString("CREDIT_BALANCE"));
                comp.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));
                comp.setAmount_due(res.getString("AMOUNT_DUE"));
                comp.setAccount_number(res.getString("ACCOUNT_NUMBER"));
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }

        return comp;
    }

    public static void updateCompany(Company comp) {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String updatecomp_sql = "UPDATE companies "
                    + "SET NAME = ? "
                    + "SET ESTABLISHMENT_DATE = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + "SET PASSWORD = ? "
                    + "SET PHONE = ? "
                    + " SET AMOUNT_DUE = ? "
                    + "SET CREDIT_LIMIT = ? "
                    + "SET CREDIT_BALANCE = ? "
                    + "SET ACCOUNT_DUE_DATE = ? "
                    + "WHERE USER_ID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updatecomp_sql);
            UtilitiesDB.setValues(preparedStatement, comp.getName(),
                    comp.getEstablishment_date(), comp.getEmail(),
                    comp.getPassword(), comp.getPhone(),
                    comp.getAmount_due(), comp.getCredit_limit(),
                    comp.getCredit_balance(), comp.getAccount_due_date(),
                    comp.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }

    }

}
