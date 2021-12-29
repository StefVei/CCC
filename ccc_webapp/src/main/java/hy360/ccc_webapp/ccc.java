/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc_webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author panagiotisk
 */
public class ccc {
    static final String url = "jdbc:mysql://localhost:3306/ccc";
    static final String user = "root";
    static final String password = "";
    static final int port = 3306;
    static final String db_name = "ccc";
    private Connection con;

    
    public ccc() throws SQLException, ClassNotFoundException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(
                url + ":" + port +"/" + db_name + "?characterEncoding=UTF-8", user, password);
    }
    
    public Connection getConnection(){
        return con;
    }
    
    
    
}
