/*
 * Mauricio Sawicki
 */
package PrimerParcial.TrenTuristico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Vendedor implements Runnable{
    
    Tren unTren;
    
    public Vendedor(Tren t1){
        this.unTren = t1;
    }
    
    public void run(){
        while(true){
            try {
                
                unTren.venderTickets();
                this.venderTicket();
                unTren.entregarTicket();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        public void venderTicket(){
        System.out.println("Vendedor: Estoy emitiendo el ticket");
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
