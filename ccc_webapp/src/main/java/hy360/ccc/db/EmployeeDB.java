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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class EmployeeDB {


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
                    + " FIRST_NAME, LAST_NAME, BIRTH_DATE, GENDER,"
                    + " EMAIL, ADDRESS, PHONE, COMPANY_USERID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    preparedStatement.RETURN_GENERATED_KEYS);

            UtilitiesDB.setValues(preparedStatement,
                    employee.getFirst_name(),
                    employee.getLast_name(),
                    employee.getBirth_date(),
                    employee.getGender(),
                    employee.getEmail(),
                    employee.getAddress(),
                    employee.getPhone(),                    
                    employee.getCompany_id()
            );

            preparedStatement.executeUpdate();

            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                employee.setEmployee_id(set.getString(1));
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

    }

    public static void deleteEmployees(String company_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();

            String del_employee = "UPDATE employees SET DELETED = ? WHERE COMPANY_USERID = ?";
            preparedStatement = con.prepareStatement(del_employee);
            UtilitiesDB.setValues(preparedStatement, true, company_id);
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
            
            String del_employee = "UPDATE employees SET DELETED = ? WHERE EMPLOYEE_ID = ?";
            preparedStatement = con.prepareStatement(del_employee);
            UtilitiesDB.setValues(preparedStatement, true, employee.getEmployee_id());
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
                    + "SET FIRST_NAME = ?, "
                    + " LAST_NAME = ?, "
                    + " BIRTH_DATE = ?, "
                    + " GENDER = ?, "
                    + " EMAIL = ?, "
                    + " ADDRESS = ?, "
                    + " PHONE = ?, "
                    + "WHERE EMPLOYEE_ID = ? ;";

            con = CccDB.getConnection();
            preparedStatement = con.prepareStatement(updateemployee_sql);
            UtilitiesDB.setValues(preparedStatement, employee.getFirst_name(),
                    employee.getLast_name(), employee.getBirth_date(), employee.getGender(),
                     employee.getEmail(), employee.getAddress(), employee.getPhone(),
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
            String sql_getemployee = "SELECT * FROM employees WHERE EMPLOYEE_ID = ?";
            con = CccDB.getConnection();

            preparedStatement = con.prepareStatement(sql_getemployee);
            UtilitiesDB.setValues(preparedStatement, employee_id);

            preparedStatement.executeQuery();
            ResultSet res = preparedStatement.getResultSet();

            if (res.next() == true) {
                employee = new Employee();
                employee.setFirst_name(res.getString("FIRST_NAME"));
                employee.setLast_name(res.getString("LAST_NAME"));
                employee.setBirth_date(res.getString("BIRTH_DATE"));
                employee.setPhone(res.getString("PHONE"));
                employee.setEmail(res.getString("EMAIL"));
                employee.setAddress(res.getString("ADDRESS"));
                String gender = res.getString("GENDER");
                employee.setGender(gender);
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
                employee.setCompany_id(res.getString("COMPANY_USERID"));
                employee.setDeleted(res.getBoolean("DELETED"));

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
                employee.setCompany_id(res.getString("COMPANY_USERID"));
                String gender = res.getString("GENDER");
                employee.setGender(gender);
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
                employee.setDeleted(res.getBoolean("DELETED"));

                employees.add(employee);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

        return employees;

    }
    
    public static List<Employee> getNonDeletedEmployees() {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            String sql_getemployees = "SELECT * FROM employees WHERE DELETED = 0;";
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
                employee.setCompany_id(res.getString("COMPANY_USERID"));
                String gender = res.getString("GENDER");
                employee.setGender(gender);
                employee.setEmployee_id(res.getString("EMPLOYEE_ID"));
                employee.setDeleted(res.getBoolean("DELETED"));

                employees.add(employee);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            UtilitiesDB.closeConnection(preparedStatement, con, EmployeeDB.class.getName());
        }

        return employees;

    }
    
    public static List<Employee> getEmployeesByProp(String columnToSearch, String value) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();
             
            insQuery.append("SELECT * FROM employees")
                .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                .append("'").append(value).append("';");
             
            
            preparedStatement = con.prepareStatement(insQuery.toString());
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
                employee.setCompany_id(res.getString("COMPANY_USERID"));

                String gender = "male".equals(res.getString("GENDER")) ? "M"
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
    
        public static List<Employee> getNonDeletedEmployeesByProp(String columnToSearch, String value) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = CccDB.getConnection();

            StringBuilder insQuery = new StringBuilder();
             
            insQuery.append("SELECT * FROM employees")
                .append(" WHERE").append(" ").append(columnToSearch).append(" = ")
                .append("'").append(value).append("'").append(" AND DELETED = 0 ;");
             
            
            preparedStatement = con.prepareStatement(insQuery.toString());
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
                employee.setCompany_id(res.getString("COMPANY_USERID"));

                String gender = "male".equals(res.getString("GENDER")) ? "M"
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
