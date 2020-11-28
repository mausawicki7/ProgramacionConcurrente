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
public class Investigador implements Runnable {
        private Sala sala;
    
    public Investigador(Sala sala) {
        this.sala = sala;
    }

    public void run() {
        sala.entrarInvestigador();
        this.investigar();
        sala.salirInvestigador();
    }

    private void investigar(){
        try {
            System.out.println(Thread.currentThread().getName()+" investiga las estrellas..");
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            Logger.getLogger(PersonalMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
