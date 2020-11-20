/*
 * Mauricio Sawicki
 */
package TP6.Pasteleria;

/**
 *
 * @author mausa
 */
public class Horno implements Runnable {

    private Pasteleria pasteleria;

    private int peso;

    public Horno(Pasteleria pasteleria, int peso) {
        this.pasteleria = pasteleria;
        this.peso = peso;
    }

    public void run() {
        
        while (true) {
            
                pasteleria.dejarPastel(peso);
            
            
        }

    }

}