/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
    public boolean  checkUserExisted(String username) throws FileNotFoundException, SQLException, ClassNotFoundException {
        List<User> lu = new ArrayList<User>();
        lu = getDataUser();
        for(User u : lu) {
                if(u.getUsername().equals(username))return false;
        }
        return true;
    }

    public	boolean checkUserPassword(User user) throws FileNotFoundException, SQLException, ClassNotFoundException {
        List<User> lu = new ArrayList<User>();
        lu = getDataUser();
        for(User u : lu) {
                if(u.getUsername().equals(user.getUsername())) {
                        if(u.getPass().equals(user.getPass()))return true;
                }
        }
        return false;		
    }
    
    public List<User> getDataUser() throws SQLException, FileNotFoundException, ClassNotFoundException {
        Connection conn =  ConnectionUtils.getSqlConnection("LTM");
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM SINHVIEN";
        ResultSet rs = statement.executeQuery(sql);
        List<User> listUser = new ArrayList<User> ();
        while(rs.next()) {
                String Fullname = rs.getString(1);
                String Password = rs.getString(2);
                listUser.add(new User(Fullname, Password));
        }


        return listUser;
    }

    public static void main(String[]args) throws FileNotFoundException, SQLException, ClassNotFoundException {
        UserDao ud = new UserDao();
        ud.getDataUser();
    }
}
