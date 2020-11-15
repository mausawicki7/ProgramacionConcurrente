/*
 * Mauricio Sawicki
 */
package TP6.SalaFumadores;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Fumador implements Runnable {

    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }//constructor
    
      public void fumar(int id) {

        System.out.println("Fumador " + id + " está fumando.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fumador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run() {
        while (true) {

            try {
                sala.entraFumar(id);
                this.fumar(id);
                System.out.println("Fumador" + id + " está fumando");
                Thread.sleep(1000);
                sala.terminaFumar(id);
            } catch (InterruptedException e) {
            }//catch
        }//while
    }//run
}// clase

