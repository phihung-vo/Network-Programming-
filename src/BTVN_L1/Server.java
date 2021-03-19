/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTVN_L1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hungh
 */
public class Server {
    private static Socket s;
    private static ServerSocket ss;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    
    public static void giaiPTB3(double a, double b, double c, double d) throws IOException {
        double a1,b1,c1,p,q,m,u,x,n,t,t1,z,u1,r,x1,x2;
        
        a1=b/a; b1=c/a; c1=d/a;
        p = b1 - (a1*a1)/3;
        q = c1 + (2*a1*a1*a1-9*a1*b1)/27;
        m = q*q/4 + p*p*p/27;
        n = q/2 + (double)Math.sqrt(m);
        if(n>=0) u = (double)Math.pow(n, 1/3);
        else u = (double)Math.pow(-n, 1/3);
        
        x = p/3/u - u - a1/3;
        t1 = 2/q*(double)Math.sqrt(-m);
        t = (1/3)*(double)Math.atan(t1);
        r = q*q/4-m;
        z = (double)Math.sqrt(r);
        u1 = (double)Math.pow(z, 1/3);
        if(q>0){
            x1 = p/3/u1*(double)Math.cos(t) - u1*(double)Math.cos(t) - a1/3;
            x2 = -p/3/u1*(double)Math.sin(t) - u1*(double)Math.sin(t);
        }else{
            x1 = -p/3/u1*(double)Math.cos(t) + u1*(double)Math.cos(t) - a1/3;
            x2 = p/3/u1*(double)Math.sin(t) + u1*(double)Math.sin(t);
        }
        if(m>=0){
            dos.writeBoolean(true);
            dos.writeDouble(x);            
        }else{
            dos.writeBoolean(false);
            dos.writeDouble(x1);
            dos.writeDouble(x2);            
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        ss = new ServerSocket(1234);
        s = ss.accept(); System.out.println("Co Client ket noi.");
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        
        
        double a, b, c, d;        
        
        while(true){
            switch(dis.readInt()){
                case 1:
                    a = dis.readDouble();
                    b = dis.readDouble();
                    c = dis.readDouble();
                    d = dis.readDouble();
                    giaiPTB3(a, b, c, d);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }        
    }
}
