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
public class PersonalMantenimiento implements Runnable {

    private Sala sala;

    public PersonalMantenimiento(Sala sala) {
        this.sala = sala;
    }

    public void run() {
        while (true) {
            sala.entrarMantenimiento();
            this.asear();
            sala.salirMantenimiento();
        }
    }

    private void asear() {
        try {
            System.out.println(Thread.currentThread().getName() + " realiza la limpieza de la sala..");
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            Logger.getLogger(PersonalMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
