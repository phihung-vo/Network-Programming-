/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_091020;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author hungh
 */
public class Server {
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server   
    private static DatagramSocket ss = null; 
    
    public static void serverProcess(){
        byte []data;
        int choice = 0;
        String message;
        
        while(true){
            try{    
                //nhan choice
                data = new byte[256];
                DatagramPacket dp0 = new DatagramPacket(data, data.length);
                ss.receive(dp0);
                message = new String(dp0.getData(), 0, dp0.getLength());
                choice = Integer.parseInt(message);
                switch(choice){
                    case 1:                                        
                        //nhan mang
                        data = new byte[512];
                        DatagramPacket dp2 = new DatagramPacket(data, data.length);
                        ss.receive(dp2);
                        message = new String(dp2.getData(), 0, dp2.getLength());
                        
                        String sub_message = message.substring(1, message.length()-1);
                        String parts[] = sub_message.split(", ");
                        int []a = new int[parts.length];
                        
                        int roll = -1;
                        for(String part : parts){
                            ++roll;
                            a[roll] = Integer.parseInt(part);
                        }
                        
                        int tmp;
                        for(int i=0; i<a.length; i++){
                            for(int j=0; j<a.length; j++){
                                if(a[j]<a[i]){
                                    tmp = a[i];
                                    a[i] = a[j];
                                    a[j] = tmp;
                                }
                            }
                        }                        
                        System.out.println("Phan tu lon thu 2 trong mang la: " + a[1]);
                        
                        break;
                    case 2:
                        System.exit(0);
                }
            }catch(IOException ex){
                System.out.println("Error 'serverProcess' fuction on server: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        try{
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ss = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
            serverProcess();
        }catch(SocketException ex){
            System.out.println("Error 'main' fuction on server: " + ex.getMessage());
        }
    }
    
}
