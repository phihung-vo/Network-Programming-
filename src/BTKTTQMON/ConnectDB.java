/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTKTTQMON;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ConnectDB {
    public static Connection SQLConnect(){
        Connection cn = null;
        String user = "sa";
        String pass = "1123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=LTM_KTTQ";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException|SQLException ex) {
            System.out.println("Error while connecting DB: " + ex.getMessage());
        }
        return cn;
    } 
}
