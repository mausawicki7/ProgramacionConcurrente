/*
 * Mauricio Sawicki
 */
package SegundoParcial.PerrosConHambre;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Perro implements Runnable {

    private Comedor comedor;
    private String nombre;

    public Perro(Comedor unComedor, String unNombre) {
        this.comedor = unComedor;
        this.nombre = unNombre;
    }

    public void run() {
        while (true) {
            try {

                System.out.println(nombre + ": Intento ocupar un plato");
                comedor.empezarComer(nombre);
                this.simularTiempoComiendo();
                comedor.terminarComer(nombre);
                System.out.println(nombre + ": Salgo del comedor");

            } catch (InterruptedException ex) {
                Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void simularTiempoComiendo() {
        try {
            Thread.sleep((int) (Math.random()) * 200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
