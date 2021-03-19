package test_ltm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5{    
    
    public static String HashMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("AES");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static void main(String args[]) {
        System.out.println(HashMD5("abc123"));
    }
}

