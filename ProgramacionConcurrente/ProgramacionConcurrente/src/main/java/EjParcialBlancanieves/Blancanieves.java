/*
 * Mauricio Sawicki
 */
package EjParcialBlancanieves;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Blancanieves implements Runnable {

    private Mesa mesa;

    public Blancanieves(Mesa mesa) {
        this.mesa = mesa;
    }

    public void run() {
        while (true) {
            try {    
                mesa.esperarPedido();
                this.prepararComida();
                mesa.servirComida();
            } catch (InterruptedException ex) {
                Logger.getLogger(Blancanieves.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void prepararComida(){
        try {
            System.out.println(Thread.currentThread().getName() + " está preparando la comida..");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Blancanieves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
