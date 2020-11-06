/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.TrenTuristico;

/**
 *
 * @author mausa
 */
public class Pasajero implements Runnable {
    
    private Tren t;

    public Pasajero(Tren t) {
        this.t = t;
    }
    
    
    public void run(){
    
        t.entrar();
        t.comprarTicket();
        t.viajar();
    
    
    }
    
    
    
    
}
