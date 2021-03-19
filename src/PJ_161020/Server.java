/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Server {
	public static void main(String[]args) throws IOException, ClassNotFoundException, SQLException {
		String name = "localhost";
		ServerSocket server = new ServerSocket(100);
		Socket socket = server.accept();
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		User user = (User) ois.readObject();
		UserDao ud = new UserDao();
		if(ud.checkUserPassword(user) ==true) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF("Dang nhap thanh cong");
			oos.close();
			oos.writeUTF("Bat dau thi trac nghiem: ");
			Connection conn = ConnectionUtils.getSqlConnection("LTM");
			String sql = "SELECT cauhoi, dapana, dapanb,dapanc,dapand, dapandung FROM CAUHOI";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List dad = new ArrayList();
			List<KT> db = new ArrayList<KT> ();
			while(rs.next()) {
				db.add(new KT(rs.getString("cauhoi"), rs.getString("dapan"),
						rs.getString("dapanb"),
						rs.getString("dapanc"),
						rs.getString("dapand")));
				dad.add(rs.getString("dapandung"));
				
			}
			oos.writeObject(db);
			List<Object> da = (List<Object>) ois.readObject();
			//lay dap an tu data
			int diem = 0;
			for(int i=0; i<da.size();i++) {
				if(dad.get(i).equals(da.get(i)))diem++;
			}
			oos.writeUTF("so diem la: "+ diem);
			
		}
		if(ud.checkUserPassword(user) ==false) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF("Dang nhap khong thanh cong");
			oos.close();
			
		}
		
	}
}
