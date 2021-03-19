/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CUOIKY_CAU1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server    
    private static DatagramSocket ds = null;
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        try{
            ds = new DatagramSocket();
            System.out.println("Client started ");
            InetAddress server = InetAddress.getByName(SERVER_IP);
            
            String option = "";
            while(true){
                while(!option.equals("0") || !option.equals("1")){
                    try{
                        System.out.println("1. Di chuyen file");
                        System.out.println("0. Thoat");
                        System.out.print("Nhap lua chon: ");
                        option = sc.nextLine().trim();
                        if(option.equals("0") || option.equals("1")) break;
                    }catch(Exception ex){
                        System.out.println("Error choose option client: " + ex.getMessage());
                    }
                }
                if(option.equals("0")) System.exit(0);
                if(option.equals("1")){
                    String filePath, fileName, path;
                    byte[] data;
                    System.err.println("Nhap duong dan File can di chuyen: ");
                    System.out.print("Nhap duong dan den thu muc chua file: ");
                    filePath = sc.nextLine();
                    System.out.print("Nhap ten file: ");
                    fileName = sc.nextLine();                        
                    path = filePath + fileName;
                    
                    //send path
                    data = path.getBytes();
                    DatagramPacket dp1 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                    ds.send(dp1);
                    
                    
                }
            }
            
        }catch(IOException ex){
            System.out.println("Error 'main' func on client: " + ex.getMessage());
        }
    }
    
}
