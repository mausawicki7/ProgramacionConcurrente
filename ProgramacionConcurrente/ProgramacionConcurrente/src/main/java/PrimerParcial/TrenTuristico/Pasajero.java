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
public class Pasajero implements Runnable {

    private Tren unTren;
    private String nombre;

    public Pasajero(Tren unTren, String nombre) {
        this.unTren = unTren;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            this.tiempoDeVenta();
            unTren.comprarTicket(nombre);
            unTren.subirseTren(nombre);
            unTren.bajarseTren(nombre);

        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tiempoDeVenta() {
        System.out.println(nombre + ": Estoy esperando a que me vendan un ticket");
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
