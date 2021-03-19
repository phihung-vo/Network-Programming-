package Multichat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private int port;
    public static ArrayList<Socket> listSK;
    
    public Server(int port){
        this.port = port;
    }
    private void execute() throws IOException {
        ServerSocket server = new ServerSocket(port);
        WriteServer write = new WriteServer();
        write.start();
        System.err.println("Server is listening.....");
        while(true){
            Socket socket = server.accept();
            System.err.println("Da ket noi voi" + socket);
            Server.listSK .add(socket);
            ReadServer read = new ReadServer(socket);
            read.start();
        }
    }
    public static void main(String[] args) throws IOException{
        Server.listSK = new ArrayList<>();
        Server server = new Server(15797);
        server.execute();
    }
}
 class ReadServer extends Thread{
    private Socket server;

    public ReadServer(Socket server) {
        this.server = server;
    }

    public void run() {
       DataInputStream dis = null;
        try {
            dis = new DataInputStream(server.getInputStream());
            while (true) {      
               String sms = dis.readUTF();
               for (Socket item: Server.listSK){
                   if(item.getPort() !=server.getPort()){
                      DataOutputStream dos = new DataOutputStream(item.getOutputStream());
                      dos.writeUTF(sms);
                   } 
               }
            System.out.println(" aaaa" + sms);
            }
            
        } catch (Exception e) {
            try {
                dis.close();
                server.close();
                
            } catch (IOException ex) {
                System.out.println("Ngat ket noi");
            } 
        }
    }     
}

class WriteServer extends Thread{
    
    @Override
    public void run() {
        DataOutputStream dos = null;
        Scanner sc = new Scanner(System.in);
        while(true){
            String sms = sc.nextLine();
            for (Socket item: Server.listSK){
                try {
                    dos = new DataOutputStream(item.getOutputStream());
                    dos.writeUTF("Server: " +sms);
                } catch (IOException ex) {
                    Logger.getLogger(WriteServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}