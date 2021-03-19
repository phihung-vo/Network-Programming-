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
public class Xu_Ly_Mang {
    
    private static final Scanner sc = new Scanner(System.in);
    
    public static void nhapMang(int [][]a, int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print("a[" +i+ "][" +j+ "] = ");
                a[i][j] = sc.nextInt();
            }
        }
    }
    
    public static void xuatMang(int [][]a, int n){
        for(int i=0;i<n;i++){
            System.out.println();
            for(int j=0;j<n;j++){
                System.out.print(a[i][j] + "\t");
            }
        }
    }
    
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
    
    public static void xuLyMang(int [][]a, int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(checkSNT(a[i][j]) == false) a[i][j] = 0;
            }
        }
    }
    
    public static void sapxepMang(int [][]a, int n){
        int []tmp = new int [n*n]; int roll=-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                roll++;
                tmp[roll] = a[i][j];
            }
        }
        int temp;
        for(int i=0;i<tmp.length;i++){
            for(int j=i+1;j<tmp.length;j++){
                if(tmp[j]<tmp[i]){
                    temp = tmp[i];
                    tmp[i]=tmp[j];
                    tmp[j]=temp;
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        
        int n;
        System.out.print("Nhap so luong phan tu cua mang 2 chieu: ");
        do{
            n = sc.nextInt();
            if(n<2) System.out.print("So luong phan tu mang khong hop le! Nhap lai: ");
        }while(n<2);
        
        int [][]a = new int [n][n];
        nhapMang(a, n);
        sapxepMang(a, n);
//        xuatMang(a, n);
    }
    
}
