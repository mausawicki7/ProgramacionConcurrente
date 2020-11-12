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

    public MedidorTemperatura(GestorDeSala unGestor) {
        this.unGestor = unGestor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.unGestor.notificarTemperatura((int) Math.random() * 10+30);

                Thread.sleep(2500);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MedidorTemperatura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
