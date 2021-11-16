/*
 * Mauricio Sawicki
 */
package FilmadoresTraductoresMonitoresLocks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Traductor implements Runnable {

    private Serie serie;
    private int miCapituloTraducir;

    public Traductor(Serie serie) {
        this.serie = serie;
    }

    public void run() {
        while (true) {
            try {
                miCapituloTraducir = serie.empezarTraduccion();
                this.simularTraduccion();
                serie.terminarTraduccion(miCapituloTraducir);
            } catch (InterruptedException ex) {
                Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void simularTraduccion() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filmador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
