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
public class Perro implements Runnable {

    Comedero comedero;

    public Perro(Comedero comedero) {
        this.comedero = comedero;
    }

    public void run() {
        while (true) {
            try {
                comedero.empezarAComer();
                this.comiendo();
                comedero.terminarDeComer();
            } catch (InterruptedException ex) {
                Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void comiendo() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
