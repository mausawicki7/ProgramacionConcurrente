/*
 * Mauricio Sawicki
 */
package PrimerParcial.Comedor;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class ControlTurnos implements Runnable {

    private Comedor unComedor;

    public ControlTurnos(Comedor c) {
        this.unComedor = c;
    }

    public void run() {
        unComedor.quienEntra(this.randomHasta());
    }

    public int randomHasta() {
        Random rand = new Random();
        return rand.nextInt(1);
    }
}
