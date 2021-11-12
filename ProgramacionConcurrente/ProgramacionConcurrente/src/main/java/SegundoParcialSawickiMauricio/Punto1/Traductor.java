/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Traductor implements Runnable {
    
     Serie serie;

    public Traductor(Serie serie) {
        this.serie = serie;
    }


    public void run() {
        while (true) {
            serie.traducir();
            this.simularTraduccion();
        }
    }
    
        public void simularTraduccion(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filmador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
