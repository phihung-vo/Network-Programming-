/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_ltm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hungh
 */
public class IO_FIle_Server {
    private static Socket s;
    private static ServerSocket ss;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    public static void main(String[] args){
        try{
            ss = new ServerSocket(1234);
            s = ss.accept(); System.out.println("Co Client ket noi.");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            while(true){
                String path = dis.readUTF();
                File f = new File(path);               

                if(!f.exists()){
                    System.out.println("Server khong tim thay path");
                }else{
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr); 
                    String line;
                    while((line = br.readLine()) != null){
                        System.out.println(line);
                    }
                    
                    
                    // Đọc dữ liệu từ File với Scanner
//                    FileInputStream fileInputStream = new FileInputStream(path);
//                    Scanner scanner = new Scanner(fileInputStream);
//
//                    try {
//                        while (scanner.hasNextLine()) {
//                            System.out.println(scanner.nextLine());
//                        }
//                    } finally {
//                        try {
//                            scanner.close();
//                            fileInputStream.close();
//                        } catch (IOException ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                    }

                    //Doc cach 3
//                    FileInputStream fileInputStream = null;
//                    BufferedReader bufferedReader = null;
//                    try {
//                        fileInputStream = new FileInputStream(path);
//                        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//                        String line = bufferedReader.readLine();
//                        while (line != null) {
//                            System.out.println(line);
//                            line = bufferedReader.readLine();
//                        }                    
//                    } catch (IOException ex) {
//                        System.out.println(ex.getMessage());
//                    } finally {
//                        bufferedReader.close();
//                        fileInputStream.close();
//                    }
                    
                }
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
