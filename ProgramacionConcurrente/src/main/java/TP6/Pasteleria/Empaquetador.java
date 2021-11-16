/*
 * Mauricio Sawicki
 */
package TP6.Pasteleria;

/**
 *
 * @author mausa
 */
public class Empaquetador implements Runnable {

    private Pasteleria pasteleria;
    private int pesoPastel;
    
    public Empaquetador(Pasteleria pasteleria) {
        this.pasteleria = pasteleria;
    }

    public void run() {
        while (true) {
            pesoPastel = pasteleria.tomarPastel();
            
            pasteleria.soltarPastel(pesoPastel);
            
            
        }

    }

}
