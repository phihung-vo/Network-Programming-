/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils  {
	
    public static Connection getSqlConnection(String database ) throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String sqlInstanceName = "SQLEXPRESS";
        String userName = "sa";
        String password = "1123";
        return getSqlConnection(hostName, sqlInstanceName, userName, password, database);	
    }

    public static Connection getSqlConnection(String hostName, String sqlInstanceName, 
                String userName, String password, String database) throws SQLException, ClassNotFoundException {

        String connectionURI = "jdbc:sqlserver://"+ hostName +":1433"+ ";instance="+ sqlInstanceName +";databaseName="+
                        database;
        Connection conn = DriverManager.getConnection(connectionURI, userName, password);
        return conn;
    }
}
