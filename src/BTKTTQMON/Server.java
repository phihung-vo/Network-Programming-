/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTKTTQMON;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    private final static int SERVER_PORT = 7; // Cổng mặc định của Echo Server    
    private static DatagramSocket ss; 
    
    //ID TRONG DATABASE DA DUOC SET IDENTITY(1,1) NGHIA LA ID TU TANG DAN
    private static boolean userExist(String usn, String pwd){
        String query = "SELECT * FROM dbo.[USER] WHERE [USER] = '" + usn + "' AND PASSWORD = '" + pwd + "'";
        try{
            Connection cn = ConnectDB.SQLConnect();
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException ex){
            System.out.println("Error 'userExist' server: " + ex.getMessage());
        }
        return false;
    }
    private static void createNewAcc(String usn, String pwd){
        String query = "INSERT INTO dbo.[USER](USER,PASSWORD) VALUES(?,?)";
        try{
            Connection cn = ConnectDB.SQLConnect();
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, usn);
            ps.setString(2, pwd);
            ps.executeUpdate();            
        }catch(SQLException ex){
            System.out.println("Error 'createNewAcc' server: " + ex.getMessage());
        }
    }
    
    public static void process_server(){
        InputStream is;
        OutputStream os;
        String message;
        byte[] data;
        int choice;
        
        while(true){
            try{
                data = new byte[256];
                DatagramPacket dp = new DatagramPacket(data, data.length);
                ss.receive(dp);
                message = new String(dp.getData(), 0, dp.getLength());
                choice = Integer.parseInt(message);
                
                switch(choice){
                    case 1:
                        data = new byte[512];
                        DatagramPacket getAcc = new DatagramPacket(data, data.length);
                        ss.receive(getAcc);
                        message = new String(getAcc.getData(), 0, getAcc.getLength());
                        
                        String[] parts = message.split("!");
                        boolean exist = userExist(parts[0], parts[1]);
                        
                        //gui account ton tai true false
                        data = String.valueOf(exist).getBytes();
                        DatagramPacket ifAccExist = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
                        ss.send(ifAccExist);
                        
                        if(exist == true){
                            //nhan path
                            data = new byte[512];
                            DatagramPacket receivePath = new DatagramPacket(data, data.length);
                            ss.receive(receivePath);
                            message = new String(receivePath.getData(), 0, receivePath.getLength());
                            
                            File f = new File(message);
                            boolean f_exist;
                            
                            if(!f.exists()){                            
                                f_exist = false;
                                
                                //gui file khong ton tai
                                data = String.valueOf(f_exist).getBytes();
                                DatagramPacket noPath = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
                                ss.send(noPath);  
                                
                            }else{
                                f_exist = true;
                                //gui file ton tai
                                data = String.valueOf(f_exist).getBytes();
                                DatagramPacket send1 = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
                                ss.send(send1);
                            }
                            
                        }
                        
                        break;
                    case 2:
                        data = new byte[512];
                        DatagramPacket getNewAcc = new DatagramPacket(data, data.length);
                        ss.receive(getNewAcc);
                        message = new String(getNewAcc.getData(), 0, getNewAcc.getLength());
                        
                        parts = message.split("!");
                        
                        //luu Tai khoan moi
                        createNewAcc(parts[0], parts[1]);
                        
                        break;
                }
                
            }catch(IOException | NumberFormatException ex){
                System.out.println("Error 'process' func on server: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        try{
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ss = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
            process_server();
        }catch(SocketException ex){
            System.out.println("Loi tao socket server ham 'main': " + ex.getMessage());
        }
    }
}
