/*
 * Mauricio Sawicki
 */
package PrimerParcial.CentroHomoterapia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Recepcionista implements Runnable {
    
    private Centro centro;
    
    public Recepcionista(Centro unCentro){
        this.centro = unCentro;
    }
    
    public void run(){
        while(true){
            try {
                this.almacenarSangre();
                centro.atenderLlamado();
                centro.terminarLlamado();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void almacenarSangre(){
        System.out.println("Soy la recepcionista estoy procesando y almacenando sangre..");
        try {
            Thread.sleep((int) (Math.random() * 200));
        } catch (InterruptedException ex) {
            Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
