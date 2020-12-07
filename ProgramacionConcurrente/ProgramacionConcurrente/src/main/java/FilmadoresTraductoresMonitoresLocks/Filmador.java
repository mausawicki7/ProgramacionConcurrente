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
public class Filmador implements Runnable {

    private Serie serie;

    public Filmador(Serie serie) {
        this.serie = serie;
    }

    public void run() {
        while (true) {
            serie.empezarFilmacion();
            this.simularFilmacion();
            serie.terminarFilmacion();
        }
    }
    
    public void simularFilmacion(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filmador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
