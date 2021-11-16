/*
 * Mauricio Sawicki
 */
package TP6.SalaFumadores;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Agente implements Runnable {

    private SalaFumadores sala;
    private Random r;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
        r = new Random();
    }

    public void run() {

        while (true) {
            int cualIngrediente = (r.nextInt(3) + 1);
            try {
                sala.colocarIngrediente(cualIngrediente);
            } catch (InterruptedException ex) {
                Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(cualIngrediente);

        }
    }
}
