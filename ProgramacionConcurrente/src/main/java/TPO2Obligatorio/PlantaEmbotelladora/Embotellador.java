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
public class Embotellador implements Runnable{
    
    private Caja caja;

    public Embotellador(Caja unaCaja) {
        this.caja = unaCaja;
    }
    
    
    public void run(){
        while(true){
            try {
                caja.embotellar();
            } catch (InterruptedException ex) {
                Logger.getLogger(Embotellador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
