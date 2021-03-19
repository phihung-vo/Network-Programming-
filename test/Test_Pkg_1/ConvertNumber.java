/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Pkg_1;

/**
 *
 * @author hungh
 */
public class ConvertNumber {
    public static final char CHAR_55 = 55;
    // A = 10, B = 11, C = 12, D = 13, E = 14, F = 15
 
    /**
     * main
     * 
     * @author viettuts.vn
     * @param args
     */
    public static void main(String[] args) {
        int n = 15;
        System.out.println("So " + n + " trong he co so 2 = "
                + ConvertNumber.convertNumber(n, 2));
        System.out.println("So " + n + " trong he co so 16 = "
                + ConvertNumber.convertNumber(n, 16));
    }
     
    /**
     * chuyen doi so nguyen n sang he co so b
     * 
     * @author viettuts.vn
     * @param n: so nguyen
     * @param b: he co so
     * @return he co so b
     */
    public static String convertNumber(int n, int b) {
        if (n < 0 || b < 2 || b > 16 ) {
            return "";
        }
         
        StringBuilder sb = new StringBuilder();
        int m;
        int remainder = n;
         
        while (remainder > 0) {
            if (b > 10) {
                m = remainder % b;
                if (m >= 10) {
                    sb.append((char) (CHAR_55 + m));
                } else {
                    sb.append(m);
                }
            } else {
                sb.append(remainder % b);
            }
            remainder = remainder / b;
        }
        return sb.reverse().toString();
    }
}
