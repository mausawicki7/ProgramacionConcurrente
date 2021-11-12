/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.PlantaEmbotelladora;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Empaquetador implements Runnable{
    
    private Caja caja;

    public Empaquetador(Caja unaCaja) {
        this.caja = unaCaja;
    }
    
    
    public void run(){
        while(true){
            try {
                caja.empaquetar();
            } catch (InterruptedException ex) {
                Logger.getLogger(Empaquetador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
