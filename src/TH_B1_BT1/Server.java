/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th_b1_bt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author VO PHI HUNG - N17DCAT031
 */
public class Server {
   
    private static ServerSocket ss;
    private static Socket s;
    private static DataInputStream din;
    private static DataOutputStream dout;
    
    //1. Tinh Tong S = 1+3+5+7 +…+ (2n+1)
    public static int tinhTongSoLe(int n){
        int sum = 0;
        for(int i=0;i<=n;i++){
            sum += 2*i + 1;
        }
        return sum;
    }
        
    //2. Tinh Tong S = 1*2 + 2*3 +…+ n*(n+1)
    public static int tinhTongTich(int n){
        int sum = 0;
        for(int i=0;i<=n;i++){
            sum += i*(i+1);
        }
        return sum;
    }
    
    
    public static void main(String[] args) throws IOException {                        
        ss= new ServerSocket(1234);
        s = ss.accept(); System.out.print("Co Client ket noi."); 
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());                
            
        while(true){
            switch(din.readInt()){
                case 1:
                    try{
                        int sum = tinhTongSoLe(din.readInt());
                        dout.writeInt(sum);
                    }catch(IOException ex){
                        System.out.println("Loi server xu ly Cau 1");
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try{
                        int sum = tinhTongTich(din.readInt());
                        dout.writeInt(sum);
                    }catch(IOException ex){
                        System.out.println("Loi server xu ly Cau 2");
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try{
                        int sum = 1-2 + 3-4 + (2*(din.readInt())+1);
                        dout.writeInt(sum);
                    }catch(IOException ex){
                        System.out.println("Loi server xu ly Cau 2");
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try{
                        int sum = din.readInt() + din.readInt();
                        dout.writeInt(sum);
                    }catch(IOException ex){
                        System.out.println("Loi server xu ly Cau 2");
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
        }                               
    }
    
}
