/*
 * Mauricio Sawicki
 */
package PrimerParcial.TrenTuristico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class ControlTren implements Runnable {

    private Tren unTren;
    private int capacidad;

    public ControlTren(Tren unTren, int capacidadMax) {
        this.unTren = unTren;
        this.capacidad = capacidadMax;
    }

    public void run() {
        while (true) {
            try {
                unTren.empezarViaje();
                this.simularViaje();
                unTren.terminarViaje();

            } catch (InterruptedException ex) {
                Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void simularViaje() {
        try {
            Thread.sleep((int) (Math.random() * 4000));
            System.out.println("Hay " + capacidad + " pasajeros ------------------------------ arranca el viaje");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
