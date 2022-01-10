/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author panagiotisk
 */
public class CccDB {
    static final String url = "jdbc:mysql://localhost:3307/ccc";
    static final String user = "root";
    static final String password = "";
    static final int port = 3307;
    static final String db_name = "ccc";
    private Connection con;

    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url + ":" + port +"/" + db_name, user, password);
    }
    
    
    
    
}
