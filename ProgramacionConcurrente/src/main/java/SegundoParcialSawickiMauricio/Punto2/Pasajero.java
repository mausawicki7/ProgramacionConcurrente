/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Pasajero implements Runnable {

    MontañaRusa montaña;

    public Pasajero(MontañaRusa montaña) {
        this.montaña = montaña;
    }

    public void run() {
        while (true) {
            try {
                montaña.subir();
                montaña.salir();
                this.recorrerParque();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void recorrerParque() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " me voy a dar una vuelta por el parque..");
        Thread.sleep((int) (Math.random() * 1000));
    }

}
