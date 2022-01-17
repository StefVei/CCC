/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import Utils_db.UtilitiesDB;
import hy360.ccc.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class EmployeeDB {

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
                Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
    }

    public static void addEmployee(Employee employee) {
        try {
            employee.checkFields();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement preparedStatement = null;
        Connection con = null;

        try {
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement("INSERT INTO employees ( "
                    + "BIRTH_DATE, EMAIL, ADDRESS, FIRST_NAME, LAST_NAME,"
                    + " PHONE, GENDER, COMPANY_USERID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    preparedStatement.RETURN_GENERATED_KEYS);

            setValues(preparedStatement,
                    employee.getBirth_date(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getPhone(),
                    employee.getGender(),
                    employee.getCompany_id()
            );

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

    }

    public static void deleteEmployee(Employee employee) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();
            String del = "DELETE FROM employees WHERE EMPLOYEE_ID = ? ";
            preparedStatement = con.prepareStatement(del);
            preparedStatement.setInt(1, Integer.valueOf(employee.getEmployee_id()));
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }
    }

    public static void updateEmployee(Employee employee) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String updateemployee_sql = "UPDATE employees "
                    + "SET FIRST_NAME = ? "
                    + "SET LAST_NAME = ? "
                    + "SET BIRTH_DATE = ? "
                    + "SET PHONE = ? "
                    + "SET EMAIL = ? "
                    + "SET ADDRESS = ? "
                    + " SET GENDER = ? "
                    + "WHERE employee_ID = ?";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updateemployee_sql);
            UtilitiesDB.setValues(preparedStatement, employee.getFirst_name(),
                    employee.getLast_name(), employee.getBirth_date(),
                    employee.getPhone(), employee.getEmail(),
                    employee.getAddress(), employee.getGender(),
                    employee.getEmployee_id());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }
    }

    public static Employee getEmployee(String employee_id) {
        Employee employee = new Employee();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getemployee = "SELECT * FROM employees WHERE employee_ID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getemployee);
            UtilitiesDB.setValues(preparedStatement, employee_id);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                employee.setFirst_name(res.getString("FIRST_NAME"));
                employee.setLast_name(res.getString("LAST_NAME"));
                employee.setBirth_date(res.getString("BIRTH_DATE"));
                employee.setPhone(res.getString("PHONE"));
                employee.setEmail(res.getString("EMAIL"));
                employee.setAddress(res.getString("ADDRESS"));

                String gender = "male".equals(res.getString("MALE")) ? "M"
                        : "female".equals(res.getString("GENDER")) ? "F"
                        : "O";

                employee.setGender(gender);
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

        return employee;

    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getemployees = "SELECT * FROM employees";
            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(sql_getemployees);
            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            while (res.next() == true) {
                Employee employee = new Employee();
                employee.setFirst_name(res.getString("FIRST_NAME"));
                employee.setLast_name(res.getString("LAST_NAME"));
                employee.setBirth_date(res.getString("BIRTH_DATE"));
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
                employee.setPhone(res.getString("PHONE"));
                employee.setEmail(res.getString("EMAIL"));
                employee.setAddress(res.getString("ADDRESS"));

                String gender = "male".equals(res.getString("MALE")) ? "M"
                        : "female".equals(res.getString("GENDER")) ? "F"
                        : "O";

                employee.setGender(gender);
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
                employees.add(employee);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

        return employees;

    }


}
