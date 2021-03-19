/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Client {
	public static void main(String[]args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		String server = "localhost";
		int port = 100;
		Socket client = new Socket(server, port);
		Scanner scanner = new Scanner(System.in);
		System.out.println("THI TRAC NGHIEM-------");
		System.out.println("Dang Nhap ");
		System.out.print("Nhap username: ");
		String username = scanner.nextLine();
		System.out.print("Nhap pass word: ");
		String pass = scanner.nextLine();
		User user = new User(username, pass);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject(user);
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		System.out.println(ois.readUTF());
		System.out.println("De Bai ");
		List<KT> db = new ArrayList<KT>();
		List da = new ArrayList();
		db = (List<KT>) ois.readObject();
		Scanner s = new Scanner(System.in);
		for(KT kt : db) {
			System.out.println(kt.getCauhoi());
			System.out.println("a."+ kt.getDa());
			System.out.println("b."+ kt.getDa());
			System.out.println("c."+ kt.getDa());
			System.out.println("d."+ kt.getDd());
			System.out.println("Nhap cau tra loi: ");
			da.add(s.nextLine());
		}
		oos.writeObject(da);
		System.out.println("Thi hoan tat");
		System.out.println(ois.readUTF());
		
		
		
	}

}
