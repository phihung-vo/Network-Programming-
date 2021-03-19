/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TH_B2_BT1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Client {    
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server    
    private static DatagramSocket ds = null;
//    private static DatagramPacket dp = null;
//    private static InetAddress server;
    
//    public static void sendInfo(DatagramPacket pkgsend, byte []data){      
//        try{
//            // Tạo gói tin gởi
//            pkgsend = new DatagramPacket(data, data.length, server, SERVER_PORT);
//            ds.send(pkgsend); // Send gói tin sang Echo Server
//        }catch(IOException ex){
//            System.out.println("Error sendInfo function on client: " + ex.getMessage());
//        }
//    }
//    public static String receiveInfo(DatagramPacket pkgreceive, byte []data){ 
//        String msg_receive = null;
//        try{
//            // Gói tin nhận
//            pkgreceive = new DatagramPacket(BUFFER, BUFFER.length);
//            ds.receive(pkgreceive); // Chờ nhận dữ liệu từ EchoServer gởi về
//            msg_receive = new String(pkgreceive.getData(), 0, pkgreceive.getLength());
//        }catch(IOException ex){
//            System.out.println("Error receiveInfo function on client: " + ex.getMessage());
//        }
//        return msg_receive;
//    }
    
    public static void moveFile(){
        Scanner sc = new Scanner(System.in);
        byte[] data;
        int choice = -1;
        String path = null, new_name = null, tmp = null;
        
        try{
            ds = new DatagramSocket();
            System.out.println("Client started ");
            InetAddress server = InetAddress.getByName(SERVER_IP);
            
            do{                
                System.out.println("1. Di chuyen File");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon: ");
                choice = sc.nextInt(); sc.nextLine();
                switch(choice){
                    case 1:
                        System.out.print("Nhap duong dan den thu muc chua file can di chuyen: ");
                        path = sc.nextLine();                        
                        
                        //gui choice
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dp0 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dp0);
                        
                        //gui old_path
                        data = path.getBytes();
                        DatagramPacket dp1 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dp1);
                        
                        //nhan check path tu server
                        DatagramPacket rcv1 = new DatagramPacket(data, data.length);
                        ds.receive(rcv1); // Chờ nhận dữ liệu từ EchoServer gởi về
                        tmp = new String(rcv1.getData(), 0, rcv1.getLength());
                        boolean check = Boolean.parseBoolean(tmp);
                        
                        if(check == false) {
                            System.out.println("Khong tim thay duong dan file!");
                        }
                        if(check == true) {
                            System.out.println("Da tim thay duong dan file");
                            path = null; new_name = null;
                            System.out.print("Nhap duong dan den thu muc moi cho file: ");
                            path = sc.nextLine();
                            System.out.print("Nhap ten file moi: ");
                            new_name = sc.nextLine();
                            //gui new_path
                            data = ( path + "\\" + new_name).getBytes();
                            DatagramPacket dp2 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                            ds.send(dp2);                            
                        }
                        break;
                    case 2:
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dpexit = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dpexit);                        
                        System.exit(0);
                }
            }while(choice != 0);
            
        }catch(IOException ex){
            System.out.println("Error fuction 'moveFile' on client: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        moveFile();
    }
    
}
