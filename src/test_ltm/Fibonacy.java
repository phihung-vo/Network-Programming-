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
public class Fibonacy {
    
    
    public static void FiboList(int n){            
        
        if(n>=0&&n<=1){
            if(n==0) System.out.print(n);
            if(n==1) System.out.print("0 1");
        }
        if(n>1){
            System.out.print("0 1 ");
            int f0 = 0, f1 = 1, fn = 1;            
            for(int i=0;i<n;i++){
                f0=f1;
                f1=fn;
                fn = f0+f1;
                System.out.print(fn + " ");
            }            
        }                
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); int n;
        System.out.print("Nhap so luong so Fibonacci ban muon in ra: ");
        do{            
            n = sc.nextInt();
            if(n<0) System.out.print("So luong phai la so nguyen duong (>=0). Nhap lai: ");
        }while(n<0);
        FiboList(n);
    }
}
