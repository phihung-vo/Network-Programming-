/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_ltm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class IO_File_Client {
    
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    
    public static void main(String[] args){        
        
        try{
            s = new Socket("localhost",1234);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());        

            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhap path: ");
                String path = sc.nextLine();

                        //D://a.txt
                dos.writeUTF(path);
                System.out.println("Da gui path");
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
