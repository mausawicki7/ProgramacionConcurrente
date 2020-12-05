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
public class ControlAtraccion implements Runnable {

    MontañaRusa montaña;
    int recorridos = 0;
    int maxRecorridos;

    public ControlAtraccion(MontañaRusa montaña, int maxRecorridos) {
        this.montaña = montaña;
        this.maxRecorridos = maxRecorridos;
    }

    public void run() {
        while (recorridos < maxRecorridos) {
            try {
                montaña.iniciarAtraccion();
                montaña.simularViaje();
                montaña.terminarViaje();
                recorridos++;
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlAtraccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
