/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import hy360.ccc.model.Citizen;
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
public class CitizenDB {

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
                Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
    }

    public static void addCitizen(Citizen citizen) {

        try {
            citizen.checkFields();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement("INSERT INTO citizen "
                    + "(LAST_NAME, FIRST_NAME, AMKA, VAT, BIRTH_DATE, GENDER "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            setValues(preparedStatement,
                    citizen.getLast_name(),
                    citizen.getFirst_name(),
                    citizen.getAmka(),
                    citizen.getVat(),
                    citizen.getBirth_date(),
                    citizen.getGender());

            preparedStatement.executeUpdate();

            String columns[] = {"CUSTOMER_ID", "ACCOUNT_NUMBER"};
            preparedStatement = con.prepareStatement("INSERT INTO customer "
                    + "USERNAME, PASSWORD, EMAIL, ADDRESS, AMOUNT_DUE, "
                    + "ACCOUNT_DUE_DATE, CREDIT_LIMIT, CREDIT_BALANCE"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", columns);

            setValues(preparedStatement, citizen.getUserName(), citizen.getPassword(),
                    citizen.getEmail(), citizen.getAddress(), citizen.getAmount_due(),
                    citizen.getAccount_due_date(), citizen.getCredit_limit(),
                    citizen.getCredit_balance());
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                BigDecimal account_number = res.getBigDecimal("ACCOUNT_NUMBER");
                citizen.setAccount_number("GR" + account_number.toString());

                int customer_id = res.getInt("CUSTOMER_ID");
                citizen.setCustomer_id(String.valueOf(customer_id));

            }
            preparedStatement = con.prepareStatement("INSERT INTO customer"
                    + " (ACCOUNT_NUMBER, CUSTOMER_ID) VALUES (?, ?)");
            setValues(preparedStatement, citizen.getAccount_number(),
                    citizen.getCustomer_id());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
        }

    }

    public static void deleteCitizen(Citizen citizen) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM citizen WHERE AMKA = ?";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(citizen.getAmka()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CitizenDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(preparedStatement, con);
        }
    }


}
