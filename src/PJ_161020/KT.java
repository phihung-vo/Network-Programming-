/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PJ_161020;

public class KT {
    private String cauhoi;
    private String a;
    private String b;
    private String c;
    private String d;
    
    public KT() {};
    public KT(String cauhoi, String da, String db, String dc, String dd) {
            super();
            this.cauhoi = cauhoi;
            this.a = da;
            this.b = db;
            this.c = dc;
            this.d = dd;
    }
    
    public String getCauhoi() {
            return cauhoi;
    }
    public void setCauhoi(String cauhoi) {
            this.cauhoi = cauhoi;
    }
    public String getDa() {
            return a;
    }
    public void setDa(String da) {
            this.a = da;
    }
    public String getDb() {
            return b;
    }
    public void setDb(String db) {
            this.b = db;
    }
    public String getDc() {
            return c;
    }
    public void setDc(String dc) {
            this.c = dc;
    }
    public String getDd() {
            return d;
    }
    public void setDd(String dd) {
            this.d = dd;
    }
    
    @Override
    public String toString() {
            return a+'\n'+ b+'\n'+ c+'\n'+ d;
    }
	
}
