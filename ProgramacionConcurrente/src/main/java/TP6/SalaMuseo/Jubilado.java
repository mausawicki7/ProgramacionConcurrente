/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Jubilado extends Persona {

    public Jubilado(GestorDeSala sala, String nom) {
        super(sala, nom);
    }

    public void run() {
        this.sala.entrarSalaJubilado();
        this.recorrerMuseo();
        this.sala.salirSala();
    }

    public void recorrerMuseo() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
