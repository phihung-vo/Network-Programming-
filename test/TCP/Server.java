/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Server {
    private static final Scanner sc = new Scanner(System.in);
    private static Socket s;
    private static ServerSocket ss;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public void startServer(){
        try{
            ss = new ServerSocket(1234);
            s = ss.accept(); System.out.println("Co Client ket noi.");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        }catch(IOException ex){
            System.out.println("Error 'startServer': " + ex.getMessage());
        }
    }    
    
    public static void main(String[] args) throws IOException {
        ss = new ServerSocket(1234);
        s = ss.accept(); System.out.println("Co Client ket noi.");
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());

        while(true){
            switch(Integer.parseInt(dis.readUTF())){
                case 1:
                    String path;
                    File f;
                    boolean exist = false;                  
                    while(exist != true){
                        path = dis.readUTF();
                        f = new File(path);
                        if(!f.isFile()){
                            exist = false;
                            dos.writeBoolean(exist);
                        }else{
                            exist = true;
                            dos.writeBoolean(exist);
                            break;
                        }
                    }
                    System.out.println("xong case 1");                    
                    
                    break;
                case 0:
                    System.exit(0);
            }       
        }   
    }
}
