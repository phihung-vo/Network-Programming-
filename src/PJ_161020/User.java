/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String pass;

    public User() {}
    public User(String username, String pass) {
            super();
            this.username = username;
            this.pass = pass;
    }
    public String getUsername() {
            return username;
    }
    public void setUsername(String username) {
            this.username = username;
    }
    public String getPass() {
            return pass;
    }
    public void setPass(String pass) {
            this.pass = pass;
    }

    public String toString() {
            return username + '\n'+ pass;
    }
}
