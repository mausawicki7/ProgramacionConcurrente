/*
 * Mauricio Sawicki
 */
package PrimerParcial.Comedor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Gato implements Runnable {

    private Comedor unComedor;
    private String nombre;

    public Gato(String nombre, Comedor c) {
        this.nombre = nombre;
        this.unComedor = c;
    }

    public void run() {
        while (true) {
            try {
                this.irAlComedor();
                unComedor.entrarGato(nombre);
                this.comer();
                unComedor.salirGato(nombre);

            } catch (InterruptedException ex) {
                Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void irAlComedor() {
        try {
            System.out.println(nombre + " estoy yendo al comedor.");
            Thread.sleep(3200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comer() {
        try {
            System.out.println(nombre + " estoy comiendo.");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
