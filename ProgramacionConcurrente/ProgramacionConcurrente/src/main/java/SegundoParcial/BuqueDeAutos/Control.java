/*
 * Mauricio Sawicki
 */
package SegundoParcial.BuqueDeAutos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Control implements Runnable {

    private Transbordador transbordador;
    
    public Control(Transbordador unTransbordador) {
        this.transbordador = unTransbordador;
    }

    public void run() {
        try {
          
            transbordador.ir();
            this.viajar();
            transbordador.volver();

        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viajar() throws InterruptedException {
        Thread.sleep(2500);
    }
}
