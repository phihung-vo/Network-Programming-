/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Standard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author hungh
 */
public class Client {
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public static void main(String[] args) {
        try{
            s = new Socket("localhost",1234);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
}
