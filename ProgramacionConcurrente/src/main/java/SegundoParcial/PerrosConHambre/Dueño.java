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
public class Dueño implements Runnable {

    private String nombre;
    private Comedor comedor;

    public Dueño(String unNombre, Comedor unComedor) {
        this.nombre = unNombre;
        this.comedor = unComedor;
    }

    public void run() {
        while (true) {
            try {
                
                comedor.empezarReponerPlatos(nombre);
                this.simularTiempoReposicion();
                comedor.terminarReponerPlatos(nombre);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Dueño.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void simularTiempoReposicion() {
        try {
            Thread.sleep((int) (Math.random()) * 200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dueño.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
