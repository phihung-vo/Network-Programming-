/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSDL_BANGDIEM;

import static TH_B2_BT1.Server.checkFilePath;
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
    
    public static void process(){
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
                        while(true){
                            data = new byte[5120];
                            DatagramPacket getStudent = new DatagramPacket(data, data.length);
                            ss.receive(getStudent);
                            message = new String(getStudent.getData(), 0, getStudent.getLength());

                            String[] parts = message.split("!");
                            double dbt1 = Double.parseDouble(parts[3]);
                            double dbt2 = Double.parseDouble(parts[4]);
                            double dbt3 = Double.parseDouble(parts[5]);
                            double dgk = (dbt1+dbt2+dbt3)/3;

                            String query = "INSERT INTO DIEM VALUES(?,?,?,?,?,?,?)";
                            try{
                                Connection cn = ConnectDB.getSqlConnection();
                                PreparedStatement ps = cn.prepareStatement(query);
                                ps.setString(1, parts[0]);
                                ps.setString(2, parts[1]);
                                ps.setString(3, parts[2]);
                                ps.setDouble(4, dbt1);
                                ps.setDouble(5, dbt2);
                                ps.setDouble(6, dbt3);
                                ps.setDouble(7, dgk);
                                ResultSet rs = ps.executeQuery();
                            }catch(SQLException | ClassNotFoundException ex){
                                System.out.println("Error query 'process' func on server: " + ex.getMessage());
                            }
                            
                            data = new byte[512];
                            DatagramPacket getConfirm = new DatagramPacket(data, data.length);
                            ss.receive(getConfirm);
                            message = new String(getConfirm.getData(), 0, getConfirm.getLength());
                            if(message.equals("n") || message.equals("N")) break;
                        }
                        
                        break;

                    case 2:
                        String query = "SELECT HoVaTen, DGK FROM DIEM";
                        try{
                            Connection cn = ConnectDB.getSqlConnection();
                            PreparedStatement ps = cn.prepareStatement(query);
                            String name, str; double mark;
                            ResultSet rs = ps.executeQuery();
                            while(rs.next()){
                                name = rs.getString("HoVaTen");
                                mark = rs.getDouble("DGK");
                                
                                str = name + "!" + mark;
                                data = str.getBytes();
                                DatagramPacket sendEachStudent = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
                                ss.send(sendEachStudent);   
                            }
                            
                        }catch(SQLException | ClassNotFoundException ex){
                            System.out.println(ex.getMessage());
                        }
                        
                        break;
                    case 3:
                        System.exit(0);
                }
            }catch(IOException ex){
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
            process();
        }catch(SocketException ex){
            System.out.println("Loi tao socket server ham 'main': " + ex.getMessage());
        }
    }
    
}
