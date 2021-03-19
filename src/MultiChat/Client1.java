package Multichat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    private InetAddress host;
    private int port;
    
    public Client1(InetAddress host, int port) {
            this.host = host;
            this.port = port;
    }
    private void execute() throws IOException {
        // Phần bổ sung
        Scanner sc = new Scanner(System.in);
        System.err.println("Nhập vào tên của bạn: ");
        String name = sc.nextLine();
        
        Socket client = new Socket(host, port);
        ReadClient read = new ReadClient(client);
        read.start();
        WriteClient write = new WriteClient(client, name);
        write.start();

    }
    public static void main(String[] args) throws IOException{
        Client1 client = new Client1(InetAddress.getLocalHost(), 15797);
        client.execute();
    }
}
    
class ReadClient1 extends Thread{
    private Socket client;

    public ReadClient1(Socket client) {
        this.client = client;
    }

    public void run() {
       DataInputStream dis = null;
        try {
            dis = new DataInputStream(client.getInputStream());
            while (true) {      
               String sms = dis.readUTF();
               System.out.println(sms);
                
            }
            
        } catch (Exception e) {
            try {
                dis.close();
                client.close();
                
            } catch (IOException ex) {
                System.out.println("Ngat ket noi");
            } 
        }
    }     
}

class WriteClient1 extends Thread{
    private Socket client;
    private String name;
    
    public WriteClient1(Socket client, String name){
        this.client=client;
        this.name = name;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        Scanner sc = null;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            sc = new Scanner(System.in);
            while(true){
                String sms = sc.nextLine();
                dos.writeUTF(name +" : " +sms);
            }
            
        } catch (Exception e) {
            try {
                dos.close();
                client.close();
            } catch (Exception ex) {
                System.out.println("Ngat ket noi Server");
            }
        }
    }
}