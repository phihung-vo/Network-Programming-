/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_ltm;

import java.util.Scanner;

/**
 *
 * @author hungh
 */
public class TEST_LTM {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner sc = new Scanner(System.in);
    
    public static boolean checkSNT(int n){
        if(n<2) return false;
        else{
            if(n==2) return true;
            else{
                for(int i=2;i<n;i++){
                    if(n%i==0) return false;
                }
            }
        }
        return true;
    }
    
    public void InSNT(int n){
        System.out.print("Nhap n = ");
        n = sc.nextInt();
        if(n<2) System.out.println("Khong ton tai so nguyen to!");
        else if(n==2) System.out.println(2);
        else{
            for(int i=2;i<=n;i++){
                if(checkSNT(i)){
                    System.out.print(i + " ");
                }
            }
        }    
    }
    
    public static void input_Array(int a[], int n){        
        for(int i=0;i<n;i++){            
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextInt();
        }        
//        int max = a[0];
//        for(int i=1;i<n;i++){
//            if(max<a[i])max=a[i];
//        }
//        System.out.println(max);
    }
    
    public static void input_Arr(float a[], int n){               
        for(int i=0;i<n;i++){            
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextFloat();
        } 
        
    }
    public static void checkSoThucKhongTrung(float a[], int n){
        
        for(int i=0;i<n;i++){            
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextFloat();
        } 
         
        boolean check;
        for(int i=0;i<n;i++){
            check = true;
            for(int j=0;j<n;j++){
                if(i != j){
                    if(a[i] == a[j]){
                        check = false;
                    }
                }
            }
            if(check == true){
                System.out.print(a[i] + " ");
            }
        }
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
            int n; float a[];
            do{
                System.out.println("Nhap so luong phan tu toi da cua mang: ");
                n = sc.nextInt();
            }while(n<1);
            a = new float[n];
            checkSoThucKhongTrung(a,n);
    }
    
    
    
}
