/*
 * Mauricio Sawicki
 */
package EjTeoriaProductorConsumidorMonitorLocks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Consumidor implements Runnable {
    
        private Buffer unBuffer;

    public Consumidor(Buffer unBuffer) {
        this.unBuffer = unBuffer;
    }

    public void run() {
            try {
                unBuffer.consumir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

