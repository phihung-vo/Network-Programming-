/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTKTTQMON;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server    
    private static DatagramSocket ds;    
    
    public static void process_client(){
        Scanner sc = new Scanner(System.in);
        String message, choice = "";
        byte[] data;
        boolean createAcc = false, valid = false;
        
        try{
            ds = new DatagramSocket();
            System.out.println("...Client Started...\n");
            InetAddress server = InetAddress.getByName(SERVER_IP);
            
            //CHON MENU
            while(valid == false){
                try{
                    System.out.println("1. Dang nhap");
                    System.out.println("2. Tao tai khoan");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap lua chon: ");
                    choice = sc.nextLine().trim();                    
                    if(choice.equals("0") || choice.equals("1") || choice.equals("2")){                        
                        valid = true;
                        //gui choice
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dp0 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dp0);
                        break;
                    }  
                    if(!choice.equals("0") || !choice.equals("1") || !choice.equals("2")){
                        System.out.println("\u001B[31m" + "Lua chon khong hop le! Chon lai!" + "\u001B[0m");
                    }
                }catch(NumberFormatException ex){
                    System.out.println("Error input option main client: " + ex.getMessage());                    
                }
            }
            //THOAT
            if(choice.equals("0")){
                System.exit(0);
            }
            //DANG NHAP
            if(choice.equals("1")){
                System.out.print("\tNhap username: ");
                String usn = sc.nextLine().trim();
                System.out.print("\tNhap password: ");
                String pwd = sc.nextLine().trim();
                
                //gui usn, pwd
                message = usn + "!" + pwd;
                data = message.getBytes();
                DatagramPacket studentInfo = new DatagramPacket(data, data.length, server, SERVER_PORT);
                ds.send(studentInfo);
                
                //nhan account ton tai true false
                data = new byte[512];
                DatagramPacket ifAccExist = new DatagramPacket(data, data.length);
                ds.receive(ifAccExist);
                message = new String(ifAccExist.getData(), 0, ifAccExist.getLength());
                
                if(Boolean.parseBoolean(message) == true){                    
                    createAcc = false;
                    
                    System.out.println("Nhap duong dan den thu muc chua file (bao gom Ten file) - Vi du D:\\foldername\\filename.txt");
                    System.out.print("Nhap duong dan: ");
                    String path = sc.nextLine().trim();
                    
                    //gui path
                    data = path.getBytes();
                    DatagramPacket sendPath = new DatagramPacket(data, data.length, server, SERVER_PORT);
                    ds.send(sendPath);
                    
                    //nhan xac thuc file ton tai hoac khong
                    data = new byte[512];
                    DatagramPacket ifexist = new DatagramPacket(data, data.length);
                    ds.receive(ifexist);
                    message = new String(ifexist.getData(), 0, ifexist.getLength());
                    
                    if(message.equals("true")){
                        System.out.println("File ton tai");
                        
                        
                    }
                    else{
                        System.out.println("File khong ton tai");
                    }
                    
                }
                if(Boolean.parseBoolean(message) == false){
                    createAcc = false;
                    
                    System.out.println("\u001B[31m" + "Tai khoan khong ton tai!" + "\u001B[0m");
                    valid = false; choice = "";
                    while(valid == false){
                        try{
                            System.out.print("Ban co muon tao tai khoan moi khong? (Yes:Y/y - No:N/n). Chon: ");
                            choice = sc.nextLine().trim();
                            if(!choice.matches("[YyNn]")){
                                System.out.println("\u001B[31m" + "Lua chon khong hop le! Chon lai!" + "\u001B[0m");
                            }
                            if(choice.matches("[YyNn]")){
                                valid = true;
                                if(choice.matches("[Yy]")){
                                    createAcc = true;
                                }
                                break;
                            }
                        }catch(NumberFormatException ex){
                            System.out.println("Error input option main client: " + ex.getMessage());                    
                        }
                    }
                    
                }
            }
            //TAO TAI KHOAN
            if(choice.equals("2") || createAcc == true){
                System.out.print("Nhap username moi: ");
                String usn = sc.nextLine().trim();
                System.out.print("Nhap password moi: ");
                String pwd = sc.nextLine().trim();
                System.out.print("Nhap lai password: ");
                String pwd_tmp = sc.nextLine();
                if(pwd.equals(pwd_tmp)){
                    message = usn+"!"+pwd;
                    data = message.getBytes();
                    DatagramPacket createNewAcc = new DatagramPacket(data, data.length, server, SERVER_PORT);
                    ds.send(createNewAcc);
                }
                                
            }
        }catch(IOException ex){
            System.out.println("Error 'process_client' server: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        process_client();
    }
    
}
