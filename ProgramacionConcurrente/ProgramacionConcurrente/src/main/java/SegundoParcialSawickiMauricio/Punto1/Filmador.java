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
public class Filmador implements Runnable {

    Serie capitulo;
    int idCap;

    public Filmador(Serie cap) {
        this.capitulo = cap;
    }

    public void run() {
        while (true) {
            capitulo.filmar();
            this.simularFilmacion();
            
        }
    }
    
    public void simularFilmacion(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filmador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
