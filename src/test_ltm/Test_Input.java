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
public class Test_Input {
    public static void main(String[] args) {
        boolean run = true;
        Scanner sc = new Scanner(System.in); // Init before loops
        String userInput; // tmp var that holds input

        while (run) {
            // do something

            System.out.println("Another action? [Y/N]");
            userInput = sc.next(); // Read first time

            // Run while user does not put Y || y || N || n
            while (!userInput.matches("[YyNn]")){
                System.out.println("Incorrect input");
                userInput = sc.next();
            }

            // User does not want more actions
            if(userInput.matches("[Nn]")){
                System.out.println("Do you wish to exit the program? [Y/Any other key]");
                String choice = sc.next();

                // Stop the program
                if (choice.toLowerCase().equals("y"))
                    run = false;
            }
        }
        sc.close(); // Close scanner outside
    }
}
