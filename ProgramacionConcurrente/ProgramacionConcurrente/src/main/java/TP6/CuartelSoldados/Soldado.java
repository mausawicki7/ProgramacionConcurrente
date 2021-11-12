/*
 * Mauricio Sawicki
 */
package TP6.CuartelSoldados;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Soldado implements Runnable {

    private Cuartel unCuartel;
    private boolean quierePostre, quiereGaseosa;

    public Soldado(Cuartel unCuartel, int quierePostre, int quiereGaseosa) {
        this.unCuartel = unCuartel;
        this.quierePostre = quierePostre == 1;
        this.quiereGaseosa = quiereGaseosa == 1;
    }


    public void run() {
        try {
            unCuartel.entrar();
            unCuartel.tomarBandeja(this.quierePostre, this.quiereGaseosa);
            unCuartel.comer();
            unCuartel.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
