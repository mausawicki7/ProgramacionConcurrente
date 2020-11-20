/*
 * Mauricio Sawicki
 */
package TP6.Pasteleria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Brazo implements Runnable {

    private Pasteleria pasteleria;

    public Brazo(Pasteleria pasteleria) {
        this.pasteleria = pasteleria;
    }

    public void run() {

        while (true) {
            
            pasteleria.retirarCaja();
            this.moverCaja();
            pasteleria.reponerCaja();
        }

    }
    
    
    private void moverCaja(){
    
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    
    }

}
