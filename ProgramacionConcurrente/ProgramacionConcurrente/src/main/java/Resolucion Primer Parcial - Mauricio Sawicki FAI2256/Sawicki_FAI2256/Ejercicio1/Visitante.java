/*
 * Mauricio Sawicki
 */
package Sawicki_FAI2256.Ejercicio1;

/**
 *
 * @author mausa
 */
public class Visitante implements Runnable {

    private Mirador mirador;

    public void run() {
        mirador.pedirAtencion();
        mirador.usarTobogan();
    }

    public Visitante(Mirador mi) {
        this.mirador = mi;
    }

}
