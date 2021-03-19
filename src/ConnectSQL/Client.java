/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Client {
    private static Connection cn;
    private static PreparedStatement ps;
    private static ResultSet rs;        
    
    public boolean checkStaffCode(String staffCode){
        String sql = "SELECT MANV FROM NHANVIEN WHERE MANV = '" + staffCode + "'";
        try{
            cn = ConnectDB.getSqlConnection("TESTDB");
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next() == true){
                return true;
            }
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public static void setData(String db){
        String sql = "insert into nhanvien(MANV,TENNV,CHUCVU) values ('02','NV02','Hung Vo','Truong phong IT')";
        try{
            cn = ConnectDB.getSqlConnection(db);
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String db = "TESTDB";
        setData(db);
//        getData();
    }
    
}
