/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Socio implements Runnable {

    Serie serie;
    int eleccion;
    Random r = new Random();

    public Socio(Serie serie) {
        this.serie = serie;
    }

    public void run() {

        this.elegirSerie();
        while(true){   
            try {      
                serie.verSerie(eleccion);
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // 1 = Ver serie en castellano, 2 = Ver serie en ingles
    public void elegirSerie() {
        System.out.println(Thread.currentThread().getName() + " eligiendo idioma..");
        eleccion = r.nextInt(2) + 1;
    }
}
