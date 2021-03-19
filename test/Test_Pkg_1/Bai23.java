/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Pkg_1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class Bai23 {
    private final Scanner sc = new Scanner(System.in);
    
    public void createTestFiles(){       
        String[] dirs = new String[2];
        dirs[0] = "D:\\TEST_FILES\\Client\\";
        dirs[1] = "D:\\TEST_FILES\\Server\\";
        try{
            for (String dir : dirs) {
                File f = new File(dir);
                if (!f.isDirectory()) {
                    Files.createDirectories(Paths.get(dir));
                }
                if(f.exists()){                    
                    String fileName = dir.substring(14,20).toLowerCase() + ".txt";
                    f = new File(dir + fileName);
                    if(!f.isFile()) Files.createFile(Paths.get(dir + fileName));
                }
            }
        }catch(IOException e){
            System.out.println("Error 'createTestFiles' Client: " + e.getMessage());
        }        
    }
    
    public void uploadFile(){
        System.out.println("MOT SO PATH DA DUOC TAO SAN:");
        System.out.println("D:\\TEST_FILES\\Client\\client.txt");
        System.out.println("D:\\TEST_FILES\\Client\\server.txt\n");
        
        String dirC = "D:\\TEST_FILES\\Client\\";
        String dirS = "D:\\TEST_FILES\\Server\\";
        String fName = "";
        String option = "";
        boolean success = false;

        while(true){
            System.out.println("---OPTIONS---");
            System.out.println("1. Upload file lên server");
            System.out.println("2. Download file về client");
            System.out.println("0. Thoát");
            
            while(!option.equals("1") || !option.equals("2") || !option.equals("0")){            
                System.out.print("Nhap lua chon: ");
                option = sc.nextLine();
                if(option.equals("1") || option.equals("2")) break;   
                if(option.equals("0")) System.exit(0);
            }

            try{
                switch(Integer.parseInt(option)){
                    case 1:         // UP FILE

                        File f = new File(dirC);
                        String[] list = f.list();
                        System.out.print("\tCAC FILE CO SAN TRONG FOLDER CLIENT\n\t");
                        for(String file : list) System.out.println("\t" + file);

                        success = false;
                        while(success == false){
                            System.out.print("\tNhap ten file: ");
                            fName = sc.nextLine();
                            f = new File(dirC + fName);
                            if(f.isFile()){
                                Files.copy(Paths.get(dirC + fName), Paths.get(dirS + fName), REPLACE_EXISTING);
//                                f = new File(dirS + fName);1
                                success = true;
                            }else System.out.println("\tFile khong ton tai!");
                        }

                        break;
                    case 2:         // DOWN FILE

                        break;
                    case 3:         // THOAT
                        System.exit(0);
                }
            }catch(IOException e){
                System.out.println("Error 'checkPath' Client: " + e.getMessage());
            }
        }        
    }
    
    public static void main(String[] args){
        Bai23 bt = new Bai23();
        bt.createTestFiles();
        bt.uploadFile();
    }
}
