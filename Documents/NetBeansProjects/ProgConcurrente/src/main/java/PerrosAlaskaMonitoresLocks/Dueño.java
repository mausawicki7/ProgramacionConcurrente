/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerrosAlaskaMonitoresLocks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Dueño implements Runnable {

    Comedero comedero;

    public Dueño(Comedero comedero) {
        this.comedero = comedero;
    }

    public void run() {
        while (true) {
            try {
                comedero.prepararComida();
                                this.dormir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Dueño.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void dormir(){
        try {
            Thread.sleep(3400);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dueño.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
