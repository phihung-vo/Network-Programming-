/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_091020;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
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
    
    public static void inputArray(int []a, int n){        
        for(int i=0; i<n; i++){
            System.out.print("\n\ta[" + i + "] = ");
            a[i] = sc.nextInt();
        }
    }    
    
    public static void process(){
        byte[] data;
        int choice;
        
        try{
            ds = new DatagramSocket();
            System.out.println("Client started ");
            InetAddress server = InetAddress.getByName(SERVER_IP);
            
            do{                
                System.out.println("1. Nhap mang");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon: ");
                choice = sc.nextInt(); sc.nextLine();
                switch(choice){
                    case 1:
                        int num, a[]; 
                        System.out.print("Nhap so luong phan tu mang: ");
                        num = sc.nextInt();
                        a = new int[num];
                        inputArray(a, num);
                                                
                        //gui choice
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dp0 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dp0);   
                        
                        //gui mang
                        data = Arrays.toString(a).getBytes();
                        DatagramPacket dp2 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dp2);
                        
                        break;
                    case 2:
                        //gui choice
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dpexit = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dpexit);
                        break;                        
                }
            }while(choice != 0);
        }catch(IOException ex){
            System.out.println("Error 'moveFile' fuction on client: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        process();
    }
    
}