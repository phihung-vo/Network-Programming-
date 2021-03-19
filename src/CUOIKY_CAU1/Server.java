/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CUOIKY_CAU1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author hungh
 */
public class Server {
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server   
    private static DatagramSocket ss = null;    
    
    public static void main(String[] args) {
        InputStream istream;
        OutputStream ostream;
        String message;
        
        try{
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            ss = new DatagramSocket(SERVER_PORT); // Tạo Socket với cổng là 7
            System.out.println("Server started ");
            System.out.println("Waiting for messages from Client ... ");
            
            //nhan path
            
            
        }catch(SocketException ex){
            System.out.println("Loi tao socket server ham 'main': " + ex.getMessage());
        }
    }
    
}
