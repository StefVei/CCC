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
import java.util.ArrayList;
import java.util.List;
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
                    + " `EMAIL`, `ADDRESS`, `PHONE`, `AMOUNT_DUE`,"
                    + " `CREDIT_LIMIT`, `CREDIT_BALANCE`, `ACCOUNT_DUE_DATE` )"
                    + " VALUES (?, ?,"
                    + "?, ?, ?, ?,"
                    + "?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            UtilitiesDB.setValues(preparedStatement,
                    company.getName(),
                    company.getEstablishment_date(),
                    
                    
                    company.getEmail(),
                    company.getAddress(),
                    company.getPhone(),
                    company.getAmount_due(),
                    
                    company.getCredit_limit(),
                    company.getCredit_balance(),
                    company.getAccount_due_date());

            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                company.setUser_id(set.getString(1));
            }

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
                comp.setName(res.getString("NAME"));
                
                comp.setEmail(res.getString("EMAIL"));
                comp.setAddress(res.getString("ADDRESS"));
                comp.setPhone(res.getString("PHONE"));
                comp.setAmount_due(res.getString("AMOUNT_DUE"));
                
                comp.setCredit_limit(res.getString("CREDIT_LIMIT"));
                comp.setCredit_balance(res.getString("CREDIT_BALANCE"));
                comp.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));
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
                    + "SET PHONE = ? "
                    + " SET AMOUNT_DUE = ? "
                    
                    + "SET CREDIT_LIMIT = ? "
                    + "SET CREDIT_BALANCE = ? "
                    + "SET ACCOUNT_DUE_DATE = ? "
                    + "WHERE USER_ID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updatecomp_sql);
            UtilitiesDB.setValues(preparedStatement, comp.getName(), comp.getEstablishment_date(),
                    comp.getEmail(), comp.getAddress(), comp.getPhone(), comp.getAmount_due(), 
                    comp.getCredit_limit(), comp.getCredit_balance(), comp.getAccount_due_date(),
                    comp.getUser_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }

    }

    public static List<Company> getAllCompanies() {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<Company> companies = new ArrayList<>();
        try {
            con = CccDB.getConnection();

            String sql = "SELECT * FROM companies";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next()) {
                Company comp = new Company();
                comp.setUser_id(res.getString("USERID"));
                comp.setEstablishment_date(res.getString("ESTABLISHMENT_DATE"));
                comp.setName(res.getString("NAME"));

                comp.setEmail(res.getString("EMAIL"));
                comp.setAddress(res.getString("ADDRESS"));
                comp.setPhone(res.getString("PHONE"));
                comp.setAmount_due(res.getString("AMOUNT_DUE"));

                comp.setCredit_limit(res.getString("CREDIT_LIMIT"));
                comp.setCredit_balance(res.getString("CREDIT_BALANCE"));
                comp.setAccount_due_date(res.getString("ACCOUNT_DUE_DATE"));

                companies.add(comp);
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, CompanyDB.class.getName());
        }

        return companies;
    }


}
