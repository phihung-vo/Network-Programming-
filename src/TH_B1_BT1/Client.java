/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th_b1_bt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author VO PHI HUNG - N17DCAT031
 */
public class Client {
    private static Socket s;
    private static DataInputStream din = null;
    private static DataOutputStream dout = null;
    private static final Scanner sc = new Scanner(System.in);
        
    public static void Menu(){
        int choice = 0, n = 0, result;
        do{
            System.out.println("--------------------------------------------");
            System.out.println("1. Tinh tong S = 1+3+5+7 +…+ (2n+1)");
            System.out.println("2. Tinh tong S = 1*2 + 2*3 +…+ n*(n+1)");
            System.out.println("3. Tinh tong S = 1-2 + 3-4 + (2n+1)");
            System.out.println("4. Tinh tong 2 so a va b");
            System.out.println("5. Tinh lap phuong 2 so a va b");
            System.out.println("6. Tinh binh phuong 2 so a va b");
            System.out.println("7. Tinh tich S1 = 1*2*3*…*n và S2= 1+2+…+n");
            System.out.println("0. Thoat");
            
            System.out.print("Vui long nhap lua chon cua ban: ");            
            do{
                choice = sc.nextInt();
                if(choice<0||choice>7) System.out.print("Lua chon khong hop le. Chon lai: ");
            }while(choice<0||choice>7);
            
            switch(choice){
                case 1:    
                    try{
                        System.out.print("Nhap vao 1 so nguyen duong: ");
                        boolean check = false;
                        while(check == false){
                            try{
                                n = sc.nextInt();
                                if(n>0) check = true;
                            }catch(Exception ex){
                                System.out.print("Ban can nhap so nguyen duong (>0): ");
                                System.out.println(ex.getMessage());
                                n = sc.nextInt();
                            }
                        }      
                        dout.writeInt(choice);
                        dout.writeInt(n);  
                                  
                        System.out.println("Ket qua nhan tu Server la: " + din.readInt());                        
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:       
                    try{
                        System.out.print("Nhap vao 1 so nguyen duong: ");
                        boolean check = false;
                        while(check == false){
                            try{
                                n = sc.nextInt();
                                if(n>0) check = true;
                            }catch(Exception ex){
                                System.out.print("Ban can nhap so nguyen duong (>0): ");
                                n = sc.nextInt();
                            }
                        }      
                        dout.writeInt(choice);                        
                        dout.writeInt(n);  
                                  
                        System.out.println("Ket qua nhan tu Server la: " + din.readInt());                        
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try{
                        System.out.print("Nhap vao 1 so nguyen duong: ");
                        boolean check = false;
                        while(check == false){
                            try{
                                n = sc.nextInt();
                                if(n>0) check = true;
                            }catch(Exception ex){
                                System.out.print("Ban can nhap so nguyen duong (>0): ");
                                n = sc.nextInt();
                            }
                        }      
                        dout.writeInt(choice);
                        dout.writeInt(n);  
                                  
                        System.out.println("Ket qua nhan tu Server la: " + din.readInt());                        
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try{
                        boolean check = false;
                        
                        int a = 0;
                        System.out.print("Nhap vao so a = ");                        
                        while(check == false){
                            try{
                                a = sc.nextInt();
                                if(a>0) check = true;
                            }catch(Exception ex){
                                System.out.print("Ban can nhap so nguyen duong (>0): ");
                                a = sc.nextInt();
                            }
                        }
                        
                        int b = 0; 
                        System.out.print("Nhap vao so b = ");                                               
                        while(check == false){
                            try{
                                b = sc.nextInt();
                                if(b>0) check = true;
                            }catch(Exception ex){
                                System.out.print("Ban can nhap so nguyen duong (>0): ");
                                b = sc.nextInt();
                            }
                        }      

                        dout.writeInt(choice);
                        dout.writeInt(a);  
                        dout.writeInt(b);
                                  
                        System.out.println("Ket qua nhan tu Server la: " + din.readInt());                        
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                   break;
            }
            
        }while(choice!=0);    
    }
    
    public static void main(String[] args) throws IOException {
        s = new Socket("127.0.0.1",1234);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        Menu();
    }
    
}
