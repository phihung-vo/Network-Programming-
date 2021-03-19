/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Standard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hungh
 */
public class Server {
    private static Socket s;
    private static ServerSocket ss;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public static void main(String[] args) {        
        try{
            ss = new ServerSocket(1234);
            s = ss.accept(); System.out.print("Co Client ket noi.");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
}
