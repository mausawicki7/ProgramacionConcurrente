/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class MedidorTemperatura implements Runnable {

    private GestorDeSala unGestor;
    private int var;

    public MedidorTemperatura(GestorDeSala unGestor) {
        this.unGestor = unGestor;
    }

    @Override
    public void run() {

        while (true) {
            var = (int) (Math.random() * 10 + 25);
            this.unGestor.notificarTemperatura(var);
            this.standBy();
        }
    }

    public void standBy() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MedidorTemperatura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
