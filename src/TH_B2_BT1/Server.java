/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TH_B2_BT1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author hungh
 */
public class Server {
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server   
    private static DatagramSocket ss = null;    
    
//    public static void sendInfo(byte []msg, InetAddress address, int port){
//        try{
//            DatagramPacket outsending = new DatagramPacket(msg, dp.getLength(), address, port);
//            ds.send(outsending);
//        }catch(IOException ex){
//            System.out.println("Error sendInfo function on client: " + ex.getMessage());
//        }
//    }
//    public String receiveInfo(DatagramPacket datapkg, byte []data){
//        String message = null;
//        try{
//            // Tạo gói tin nhận
//            datapkg = new DatagramPacket(BUFFER, BUFFER.length);
//            ds.receive(datapkg); // Chờ nhận gói tin gởi đến 
//            // Lấy dữ liệu khỏi gói tin nhận
//            message = new String(datapkg.getData(), 0, datapkg.getLength());
//        }catch(IOException ex){
//            System.out.println("Error receiveInfo function on client: " + ex.getMessage());
//        }
//        return message;
//    }
    
    public static void checkFilePath(){ 
        InputStream istream;
        OutputStream ostream;
        int choice;
        String message;
        
        while(true){
            try{    
                //nhan choice
                byte []rcv_choice = new byte[256];
                DatagramPacket dpt0 = new DatagramPacket(rcv_choice, rcv_choice.length);
                ss.receive(dpt0);
                message = new String(dpt0.getData(), 0, dpt0.getLength());
                choice = Integer.parseInt(message);
                switch(choice){
                    case 1:
                        //nhan old_path
                        byte []rcv_old_path = new byte[256];
                        DatagramPacket dpt1 = new DatagramPacket(rcv_old_path, rcv_old_path.length);
                        ss.receive(dpt1);
                        message = new String(dpt1.getData(), 0, dpt1.getLength()); System.out.println(message);
                        
                        File old_file = new File(message);
                        byte []send_check;
                        if(!old_file.exists()){                            
                            send_check = String.valueOf(false).getBytes();
                            DatagramPacket send1 = new DatagramPacket(send_check, send_check.length, dpt1.getAddress(), dpt1.getPort());
                            ss.send(send1);                      
                        }else{
                            send_check = String.valueOf(true).getBytes();
                            DatagramPacket send1 = new DatagramPacket(send_check, send_check.length, dpt1.getAddress(), dpt1.getPort());
                            ss.send(send1);   
                            
                            //nhan new_path
                            byte []rcv_new_path = new byte[256];
                            DatagramPacket dpt2 = new DatagramPacket(rcv_new_path, rcv_new_path.length);
                            ss.receive(dpt2);
                            message = new String(dpt2.getData(), 0, dpt2.getLength());       System.out.println(message);                     
                            
                            File new_file = new File(message);
                            istream = new FileInputStream(old_file);
                            ostream = new FileOutputStream(new_file);

                            byte []buffer = new byte[1024];
                            int length;
                            while ((length = istream.read(buffer)) > 0) {
                                ostream.write(buffer, 0, length);
                            }
                            istream.close();
                            ostream.close();                        
                            old_file.delete();
                            System.out.println("File duoc di chuyen thanh cong.");
                        }
                        break;
                    case 2:
                        System.exit(0);
                }
                
            }catch(IOException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        try{
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ss = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
            checkFilePath();
        }catch(SocketException ex){
            System.out.println("Loi tao socket server ham 'main': " + ex.getMessage());
        }
    }
}
