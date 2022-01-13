/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Company;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class CompanyDB {

    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    private static void closeConnection(PreparedStatement preparedStatement, Connection con) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
    }

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

            preparedStatement = con.prepareStatement("INSERT INTO company "
                    + "(NAME, LOGOTYPE, ESTABLISHMENT_DATE)"
                    + "VALUES (?, ?, ?)", generatedColumns);

            setValues(preparedStatement,
                    company.getName(),
                    company.getLogotype(),
                    company.getEstablishment_date());

            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                int company_id = res.getInt(1);
                company.setCompany_id(String.valueOf(company_id));
            }
            preparedStatement = con.prepareStatement("INSERT INTO company (company_ID) VALUES (?)");
            setValues(preparedStatement, company.getCompany_id());
            preparedStatement.executeUpdate();

            String columns[] = {"CUSTOMER_ID", "ACCOUNT_NUMBER"};
            preparedStatement = con.prepareStatement("INSERT INTO customer "
                    + "USERNAME, PASSWORD, EMAIL, ADDRESS, AMOUNT_DUE, "
                    + "ACCOUNT_DUE_DATE, CREDIT_LIMIT, CREDIT_BALANCE"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", columns);

            setValues(preparedStatement, company.getUserName(), company.getPassword(),
                    company.getEmail(), company.getAddress(), company.getAmount_due(),
                    company.getAccount_due_date(), company.getCredit_limit(),
                    company.getCredit_balance());
            preparedStatement.executeUpdate();
            res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                BigDecimal account_number = res.getBigDecimal("ACCOUNT_NUMBER");
                company.setAccount_number("GR" + account_number.toString());

                int customer_id = res.getInt("CUSTOMER_ID");
                company.setCustomer_id(String.valueOf(customer_id));

            }
            preparedStatement = con.prepareStatement("INSERT INTO customer"
                    + " (ACCOUNT_NUMBER, CUSTOMER_ID) VALUES (?, ?)");
            setValues(preparedStatement, company.getAccount_number(),
                    company.getCustomer_id());
            preparedStatement.executeUpdate();



        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
        }

    }

    public static void deleteCompany(Company company) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM companies WHERE USERID = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(company.getCompany_id()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
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
            String findComp = "SELECT FROM companies WHERE "
                    + columnToSearch + " = ?";
            preparedStatement = con.prepareStatement(findComp);
            setValues(preparedStatement, value);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();
            if (res.next()) {
                comp = new Company();
                comp.setCompany_id(res.getString("USERID"));
                comp.setEstablishment_date(res.getString("ESTABLISHMENT_DATE"));
                comp.setLogotype(res.getString("LOGOTYPE"));
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
        }

        return comp;
    }

}
