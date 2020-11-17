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
public class Productor implements Runnable {

    private Buffer unBuffer;

    public Productor(Buffer unBuffer) {
        this.unBuffer = unBuffer;
    }

    public void run() {
        while(true)
            try {
                unBuffer.producir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
