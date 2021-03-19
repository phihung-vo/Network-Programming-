/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BT_CHUONG5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hungh
 */
public class Server {
    private static Socket s;
    private static ServerSocket ss;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public static String checkLogin(String usn, String pwd){
        String studentCode = null;
        String query = "SELECT MASV, UserName, PassWord FROM SINHVIEN WHERE UserName = '" + usn + "' AND PassWord = '" + pwd + "'";        
        try{
            Connection cn = ConnectDB.getSqlConnection();
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next() == true){
                studentCode = rs.getString("MASV");
                dos.writeInt(1);
            }else{
                dos.writeInt(0);
            }
        }catch(IOException | SQLException | ClassNotFoundException ex){
            System.out.println("Error 'checkLogin' func on server: " + ex.getMessage());
        }
        return studentCode;
    }
    
    public static void saveMark(String studentCode, float diem){        
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();        
        String query = "INSERT INTO BANGDIEM VALUES (?,?,?,?,?)";
        try{            
            Connection cn = ConnectDB.getSqlConnection();
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, studentCode);
            ps.setInt(2, 1);
            ps.setString(3, df.format(date));
            ps.setFloat(4, diem);
            ps.setString(5, "Thu 1");
            ps.execute();
        }catch(SQLException | ClassNotFoundException ex){
            System.err.println("Error 'savemark' func on server: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {        
        try{
            ss = new ServerSocket(1234);
            s = ss.accept(); System.out.println("Co Client ket noi.");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
                        
            String studentCode = null;
            while(true){
                boolean check = false;
                while(check == false){
                    studentCode = checkLogin(dis.readUTF(), dis.readUTF());
                    if(studentCode != null){
                        check = true;
                    }
                }
                if(check == true){                    
                    //Lam bai trac nghiem             
                    int maxQst = dis.readInt();   
                    int right_ans = 0;
                    ArrayList<Question> questions = new ArrayList<>();
                                        
                    try{
                        String query = "SELECT TOP " + maxQst + " * FROM BODE ORDER BY NEWID()";
                        Connection cn = ConnectDB.getSqlConnection();
                        PreparedStatement ps = cn.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            Question qst = new Question();
                            qst.setContent(rs.getString("NOIDUNG"));
                            qst.setA(rs.getString("A"));
                            qst.setB(rs.getString("B"));
                            qst.setC(rs.getString("C"));
                            qst.setD(rs.getString("D"));
                            qst.setAns(rs.getString("DAP_AN"));
                            questions.add(qst);
                        }
                    }catch(SQLException | ClassNotFoundException ex){
                        System.err.println("Error load QSTList on server!");
                    }
                    System.out.println("Get Question List successfully");
                    
                    for(int i=0; i<questions.size(); i++){                     
                        dos.writeUTF(questions.get(i).getContent());
                        dos.writeUTF(questions.get(i).getA());
                        dos.writeUTF(questions.get(i).getB());
                        dos.writeUTF(questions.get(i).getC());
                        dos.writeUTF(questions.get(i).getD());

                        if(dis.readUTF().equals(questions.get(i).getAns())){
                            right_ans++;                            
                        }
                    }
                    dos.writeInt(right_ans);
                    saveMark(studentCode, (float)right_ans);
                }
            }
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
}
