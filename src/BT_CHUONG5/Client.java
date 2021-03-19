package BT_CHUONG5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Client {
    private static Socket s;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;
    private static final Scanner sc = new Scanner(System.in);
    
    public static boolean login(){
        String usn, pwd;        
        try{
            System.out.print("Username: ");
            usn = sc.nextLine().trim();
            System.out.print("Password: ");
            pwd = sc.nextLine().trim();
            
            dos.writeUTF(usn);
            dos.writeUTF(pwd);
            
            if(dis.readInt() == 1){
                return true;
            }
        }catch(IOException ex){
            System.err.println("Error 'login' func on client: " + ex.getMessage());
        }
        return false;
    }
    
    private static void showResult(String str, int mark){
        System.out.println("\u001B[31m" + "\n-----Ket qua lam bai thi cua ban-----" + "\u001B[0m");
        String[] parts = str.split(",");
        for(int i=0; i<parts.length; i++){
            System.out.println((i+1) + " - " + parts[i]);
        }
        System.out.print("\u001B[31m" + "DIEM: " + mark + "\u001B[0m" + "\n\n");
    }
    
    public static void main(String[] args) throws IOException {
        s = new Socket("127.0.0.1",1234);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        
        int option = -1;
        while(true){
            while(option != 0 || option != 1){
                try{
                    System.out.println("-----CHUONG TRINH THI TRAC NGHIEM-----");
                    System.out.println("1. Thi trac nghiem");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap lua chon: ");
                    option = Integer.parseInt(sc.nextLine().trim());
                    if(option == 0 || option == 1) break;
                    else System.out.print("Lua chon khong hop le! Chon lai: ");
                }catch(NumberFormatException ex){
                    System.out.println("Error input option main client: " + ex.getMessage());                    
                }
            }            
            if(option == 0) System.exit(0);
            if(option == 1){
                boolean check = false;
                while(check == false){
                    if(login() == true){
                        check = true;
                        System.out.println("Account valid... OK.");
                    }else{
                        System.err.println("Username or password is not valid!");
                    }
                }

                if(check == true){
                    int maxQst = 3, count = 0;
                    String nd, a, b, c, d, choice, str = "";
                    
                    dos.writeInt(maxQst);
                    
                    while(count < maxQst){                 
                        nd = dis.readUTF();
                        a = dis.readUTF();
                        b = dis.readUTF();
                        c = dis.readUTF();
                        d = dis.readUTF();

                        System.out.print("\nCau " + ++count + ": ");
                        System.out.println(nd);
                        System.out.println("a. " + a);
                        System.out.println("b. " + b);
                        System.out.println("c. " + c);
                        System.out.println("d. " + d);

                        System.out.print("Nhap lua chon cua ban: ");
                        boolean valid_ans = false;
                        while(valid_ans == false){                        
                            choice = sc.nextLine().trim();
                            while (!choice.matches("[AaBbCcDd]")){
                                System.out.print("Lua chon khong hop le! Nhap lai lua chon: ");
                                choice = sc.nextLine().trim();
                            }
                            if(choice.matches("[AaBbCcDd]")){
                                valid_ans = true;
                                System.err.println("Lua chon cua ban la: " + choice.toUpperCase());
                                dos.writeUTF(choice.toUpperCase());
                                str += choice.toUpperCase() + ",";
                            }
                        }                        
                    }
                    showResult(str, dis.readInt());                
                }
            }
        }
        
    }
}