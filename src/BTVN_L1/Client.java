/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTVN_L1;

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
    
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
       
    public static void nhapHeSo() throws IOException {
        Scanner sc = new Scanner(System.in);
        double a, b, c, d;        
        int choice;
        
        do{
            System.out.println("1. Giai PTB3");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:                   
                    System.out.print("Nhap vao so a: ");
                    a = sc.nextDouble();
                    System.out.print("Nhap vao so b: ");
                    b = sc.nextDouble();
                    System.out.print("Nhap vao so c: ");
                    c = sc.nextDouble();
                    System.out.print("Nhap vao so d: ");
                    d = sc.nextDouble();
                    
                    dos.writeInt(choice);
                    dos.writeDouble(a);
                    dos.writeDouble(b);
                    dos.writeDouble(c);
                    dos.writeDouble(d);
                    
                    if(dis.readBoolean() == true){
                        System.out.println("Phuong trinh co nghiem la: " + dis.readDouble());
                    }else{
                        System.out.println("Phuong trinh co nghiem phuc: " + dis.readDouble() + "+" + "(" + dis.readDouble() + ")" + "*1");
                    }
                    
                    break;
                case 0:
                    dos.writeInt(choice);
                    break;
            }            
        }while(choice != 0);
    }
    
    public static void main(String[] args) throws IOException {
        s = new Socket("localhost",1234);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());  
        nhapHeSo();
    }
}
