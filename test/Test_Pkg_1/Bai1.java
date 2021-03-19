/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Pkg_1;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author hungh
 */
public class Bai1 {
    private final Scanner sc = new Scanner(System.in);
    
    public int tongChuSo(int n){
        n = Integer.parseInt(String.valueOf(n).trim());
        int sum = 0;        
        while(n > 0){
            sum += n%10;
            n /= 10;
        }
        return sum;
    }
    
    public boolean checkSNT(int n){
        boolean snt = true;
        if(n < 2) snt = false;
        else if(n == 2) snt = true;
        else{
            for(int i=2; i<n; i++){
                if(n%i == 0){
                    snt = false;
                    break;
                }
            }
        }
        return snt;
    }
    
    public String phanTichThuaSoNguyenTo(int n){
        String str = "";
        int tmp = 0;
        while(n > 0){
            for(int i=0; i<=n; i++){
                if(checkSNT(i)){
                    if(n%i == 0){
                        tmp = i;
                        str += i + " x ";
                        break;
                    }
                }
            }
            n /= tmp;
        }   
        return str;
    }    
    
    public boolean checkSoThuanNghich(int n) {
        n = 100021;
        StringBuilder xau = new StringBuilder();    System.out.println(xau.toString());
        String str = "" + n;                        System.out.println(str);
        xau.append(str);                            System.out.println(xau.toString());
        String check = "" + xau.reverse();          System.out.println(check);
        if (str.equals(check))
            return true;
        else
            return false;
    }
    
    public void addElement(Map<Float, Float> map, float element) {
        if (map.containsKey(element)) {
            float count = map.get(element) + 1;
            map.put(element, count);
        } else {
            map.put(element, (float)1);
        }
    }
    
    public void soLanXuatHienCacPhanTu(){
        System.out.print("Nhập số phần tử của mảng: ");
        int n = sc.nextInt();
        // khởi tạo arr
        float[] arr = new float [n];
        System.out.println("Nhập các phần tử của mảng: ");
        for (int i = 0; i < n; i++) {
            System.out.printf("a[%d] = ", i);
            arr[i] = sc.nextFloat();
        }
        // tìm số lần xuất hiện của các phần tử
        Map<Float, Float> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            addElement(map, arr[i]);
        }
        System.out.print("Các phần tử xuất hiện 1 lần: ");
        for (Float key : map.keySet()) {
            if (map.get(key) == 2) {
                System.out.print(key + " ");
            }
        }
    }                
    
    public void demSoTuCuaXauKyTu(){
        String str = "a  acd dfg 3 df gdf45   fgd    ";
        str = str.replaceAll("\\s\\s+", " ").trim();
        String[] parts = str.split(" ");
        for(String part : parts) System.out.print(part + ", ");
        System.out.println(parts.length);
    }
    
    public void lietKeSNT5ChuSoCoTongBangS(){
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=10000; i<=99999; i++){
            if(checkSNT(i) && tongChuSo(i) == 8){
                System.out.println(i);
                arr.add(i);
            }
        }
        
        System.out.println(arr.size());
    }
    
    public int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    
    public void lietKeFibonacciNhoHonNLaSNT(){
        System.out.print("Nhập số tự nhiên n = ");
        int n = sc.nextInt();
        System.out.printf("Các số fibonacci nhỏ hơn %d và "
                + "là số nguyên tố: ", n);
        int i = 0;
        while (fibonacci(i) <= n) {
            int fi = fibonacci(i);
            if (checkSNT(fi)) {
                System.out.print(fi + " ");
            }
            i++;
        }
    }
    
    public String inHoaMoiChuCai(){
        String str = "  you    are a good   man";
        str = str.replaceAll("\\s\\s+", " ").trim();
        String[] arr = str.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                .append(arr[i].substring(1)).append(" ");
        }          
        return sb.toString().trim();
    }
    
    public void bai15(){
        System.out.print("Nhap vao so dong cua mang: ");
        int m = sc.nextInt();
        System.out.print("Nhap vao so cot cua mang: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        int[][] arr = new int[m][n];
        String num = "";
        System.out.println("Nhap cac phan tu cho mang: ");
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.printf("\ta[%d][%d] = ", i, j);
                while(!num.matches("\\d+")){
                    num = sc.nextLine();
                    if(!num.matches("\\d+"))
                        System.out.printf("\tNhap lai a[%d][%d] = ", i, j);
                    else arr[i][j] = Integer.parseInt(num);
                }                                
                num = "";
            }
        }        
        
        int max = arr[0][0], dong = 0, cot = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j] > max){
                    max = arr[i][j];
                    dong = i;
                    cot = j;
                }
            }
        }
        System.out.printf("Phan tu lon nhat trong mang: %d - Vi tri: dong %d cot %d\n", max, dong, cot);        
        
        System.out.println("Cac phan tu la so nguyen to cua ma tran");
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!checkSNT(arr[i][j])){ 
                    System.out.print(0 + "   ");
                }else System.out.print(arr[i][j] + "   ");                                
            }
            System.out.println();
        }
        
        System.out.println("Sap xep mang tang dan");
        int tmp = 0;
        int k = m*n;
        for(int i=0; i<k-1; i++){
            for(int j=i+1; j<k; j++){
                if(arr[i/n][i%n] > arr[j/n][j%n]){
                    tmp = arr[i/n][i%n];
                    arr[i/n][i%n] = arr[j/n][j%n];
                    arr[j/n][j%n] = tmp;
                }                                                                  
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + "   ");                                
            }
            System.out.println();
        }
        
    }        
    
    public void bai16(){
        System.out.print("Nhap so luong phan tu cua mang: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        int[] arr = new int[n];
        String num = "";
        System.out.println("Nhap cac phan tu cho mang: ");
        for(int i=0; i<n; i++){
            System.out.printf("\ta[%d] = ", i);
            while(!num.matches("\\d+")){
                num = sc.nextLine();
                if(!num.matches("\\d+"))
                    System.out.printf("\tNhap lai a[%d] = ", i);
                else arr[i] = Integer.parseInt(num);
            }                                
            num = "";
        }        
        
        int max = arr[0], vt = 0;
        for(int i=0; i<n; i++){
            if(arr[i] > max){
                max = arr[i];
                vt = i;
            }
        }
        System.out.printf("Phan tu lon thu nhat trong mang: %d - Vi tri: %d\n", max, vt);     
        
        int max2 = arr[0];
        for(int i=0; i<n; i++){
            if(arr[i] == max) continue;
            if(arr[i] > max2){
                max2 = arr[i];
                vt = i;
            }
        }
        System.out.printf("Phan tu lon thu hai trong mang: %d - Vi tri: %d\n", max2, vt);       
        
        System.out.print("Cac phan tu la so nguyen to cua mang: ");
        for(int i=0; i<n; i++){
            if(!checkSNT(arr[i])){ 
                System.out.print(0 + " ");
            }else System.out.print(arr[i] + " ");          
        }
        
        System.out.println("\nSap xep mang giam dan");
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(arr[i] < arr[j]){
                    max = arr[i];
                    arr[i] = arr[j];
                    arr[j] = max;
                }
            }
        }
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
                
        
        System.out.print("\nNhap gia tri can them vao mang: ");
        int value = sc.nextInt();
        System.out.print("Nhap vi tri can them vao mang: ");
        vt = sc.nextInt();
        int[] tmp = new int[arr.length+1];
        System.out.println(tmp.length);
        for(int i=0; i<tmp.length; i++){
            if(i < vt) tmp[i] = arr[i];
            if(i == vt) tmp[i] = value;
            if(i > vt){
                tmp[i] = arr[i-1];                
            }
        }               
        for(int i=0; i<tmp.length; i++){
            System.out.print(tmp[i] + " ");
        }
        
    } 
    
    public static int nhap(){
        Scanner input= new Scanner(System.in);
        boolean check= false;
        int n=0;
        while(!check){
            System.out.print("Nhap so: ");
            try{
                n= input.nextInt();
                check = true;
            }catch(Exception e){
                System.out.print("Ban phai nhap so! hay nhap lai...");
                input.nextLine();
                System.out.println();
            }
        }
        return (n);
    }    
    
    public static void main(String[] args) {
        Bai1 bt = new Bai1();
        
    }
}
