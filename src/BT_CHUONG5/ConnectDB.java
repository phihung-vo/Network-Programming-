/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT_CHUONG5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {
    
    public static Connection getSqlConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String sqlInstanceName = "MSSQLSERVER";
        String userName = "sa";
        String password = "1123";
        String database = "KIEMTRALTM";                                             //The NAME of TABLE in this DB is "DIEM"
        return getSqlConnection(hostName, sqlInstanceName, userName, password, database);	
    }

    public static Connection getSqlConnection(String hostName, String sqlInstanceName, 
                String userName, String password, String database) throws SQLException, ClassNotFoundException {

        String connectionURI = "jdbc:sqlserver://" + hostName + ":1433" + ";databaseName="+
                        database;
        Connection conn = DriverManager.getConnection(connectionURI, userName, password);
        return conn;
    }
}
