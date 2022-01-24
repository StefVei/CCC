/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author panagiotisk
 */
public class CccDB {
    static final String url = "jdbc:mysql://localhost:3306/ccc";
    static final String user = "root";
    static final String password = "";
    static final int port = 3306;
    static final String db_name = "ccc";
    private Connection con;

    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        return DriverManager.getConnection(url, user, password);

    }
    
    
    
    
}
