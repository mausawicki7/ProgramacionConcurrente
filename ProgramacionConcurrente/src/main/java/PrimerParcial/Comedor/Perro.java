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
public class Perro implements Runnable {

    private Comedor unComedor;
    private String nombre;

    public Perro(String nombre, Comedor c) {
        this.nombre = nombre;
        this.unComedor = c;
    }

    public void run() {
        while (true) {
            try {

                this.irAlComedor();
                unComedor.entrarPerro(nombre);
                this.comer();
                unComedor.salirPerro(nombre);

            } catch (InterruptedException ex) {
                Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void irAlComedor() {
        try {
            System.out.println(nombre + " estoy yendo al comedor.");
            Thread.sleep(3000);
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
