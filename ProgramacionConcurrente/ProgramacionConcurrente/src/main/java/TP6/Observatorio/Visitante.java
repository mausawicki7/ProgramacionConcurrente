/*
 * Mauricio Sawicki
 */
package TP6.Observatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Visitante implements Runnable {
    private Sala sala;   
    private boolean esDiscapacitado;
    
    public Visitante(Sala sala, int tieneSilla) {
        this.sala = sala;
        this.esDiscapacitado = (tieneSilla == 1);
    }
    
    public void run(){
        
        sala.entrarVisitante(esDiscapacitado);
        this.estudiarEstrellas();
        sala.salir(esDiscapacitado);
    }
    
    private void estudiarEstrellas(){
        try {
            System.out.println(Thread.currentThread().getName()+" contempla las estrellas..");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
