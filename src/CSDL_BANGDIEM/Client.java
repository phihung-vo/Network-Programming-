/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSDL_BANGDIEM;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7; // Cổng mặc định của Echo Server    
    private static DatagramSocket ds;
    
    
    public static void input(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        byte[] data;
        
        try{
            ds = new DatagramSocket();
            System.out.println("Client Started");
            InetAddress server = InetAddress.getByName(SERVER_IP);
            
            do{
                System.out.println("1. Nhap Thong tin va Diem cho tung Sinh vien");
                System.out.println("2. Xem Danh Sach Diem");
                System.out.println("0. Thoat");
                
                do{
                    try{
                        System.out.print("Lua chon cua ban: ");
                        choice = sc.nextInt(); sc.nextLine();
                    }catch(Exception ex){
                        System.err.println("Lua chon khong hop le!" + ex.getMessage());
                    }
                }while(choice<0 && choice >2);
                
                switch(choice){
                    case 1:
                        String resume = null;
                        while(true){
                            System.out.print("Nhap Ho ten: ");
                            String name = sc.nextLine();
                            System.out.print("Nhap Ma Sinh Vien: ");
                            String studentCode = sc.nextLine();
                            System.out.print("Nhap Ma Lop: ");
                            String classID = sc.nextLine();
                            System.out.print("Nhap Diem Bai Tap 1: ");
                            double dbt1 = sc.nextDouble();
                            System.out.print("Nhap Diem Bai Tap 2: ");
                            double dbt2 = sc.nextDouble();
                            System.out.print("Nhap Diem Bai Tap 3: ");
                            double dbt3 = sc.nextDouble();
                            sc.nextLine();

                            //gui choice
                            data = String.valueOf(choice).getBytes();
                            DatagramPacket dp0 = new DatagramPacket(data, data.length, server, SERVER_PORT);
                            ds.send(dp0);

                            //gui Thong tin Sinh vien
                            String msg = name + "!" + studentCode + "!" + classID + "!" + dbt1 + "!" + dbt2 + "!" + dbt3;
                            data = msg.getBytes();
                            DatagramPacket studentInfo = new DatagramPacket(data, data.length, server, SERVER_PORT);
                            ds.send(studentInfo);
                            
                            do{
                                System.out.print("Ban muon nhap tiep Sinh vien khong? Yes(nhap 'y') | No(nhap 'n') : ");
                                resume = sc.nextLine();
                                resume = resume.trim();
                                if(resume.equals("n") || resume.equals("N")) break;
                                if(!resume.equals("y") || !resume.equals("n") || !resume.equals("Y") || !resume.equals("N")){
                                    System.err.println("Lua chon khong hop le! Vui long chon lai!"); continue;
                                }                                    
                            }while(resume.equals("y") || resume.equals("n") || resume.equals("Y") || resume.equals("N"));
                            
                            if(resume.equals("n") || resume.equals("N")){
                                //gui xac thuc
                                data = resume.getBytes();
                                DatagramPacket ifExit = new DatagramPacket(data, data.length, server, SERVER_PORT);
                                ds.send(ifExit);
                                break;
                            }
                        }
                        
                        break;
                    case 2:
//                        while()
                        break;
                    case 3:
                        data = String.valueOf(choice).getBytes();
                        DatagramPacket dpexit = new DatagramPacket(data, data.length, server, SERVER_PORT);
                        ds.send(dpexit);                        
                        System.exit(0);                        
                        
                }
                
            }while(choice != 0);
                    
        }catch(IOException ex){
            System.err.println("Error 'input' func on client: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        input();
    }
    
}
