/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Client {
    private static final Scanner sc = new Scanner(System.in);
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;    
    
    public static void actions(){
        try{            
            while(true){                                        
                System.out.println("1. Thao tac file");
                System.out.println("0. Thoat");
                String opt = "";
                while(!opt.equals("1") || !opt.equals("0")){                                       
                    System.out.print("Nhap lua chon: ");
                    opt = sc.nextLine();
                    if(opt.equals("1") || opt.equals("0")) break;
                } 
                dos.writeUTF(opt);
                switch(Integer.parseInt(opt)){
                    case 1:
                        String path = "";
                        boolean exist = false;                  
                        while(exist != true){
                            System.out.print("Nhap duong dan file: ");
                            path = sc.nextLine().trim();
                            dos.writeUTF(path);
                            exist = dis.readBoolean();
                            if(exist == true) break;
                        }
                        if(exist == true) System.out.println("Thao tac file");
                        
                        break;
                    case 0:
                        System.exit(0);
                }
            }
        }catch(IOException ex){
            System.out.println("Error 'actions': " + ex.getMessage());
        }        
    }
    
    public static void nhapFileClient(){
        while(true){
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        s = new Socket("localhost",1234);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());     
        
        actions();
    }
}
