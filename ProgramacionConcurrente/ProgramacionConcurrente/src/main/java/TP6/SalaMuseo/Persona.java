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
public class Persona implements Runnable {

    private String nombre;
    GestorDeSala sala;


    public Persona(GestorDeSala sala, String nom){
        this.nombre = nom;
        this.sala = sala;

    }
    
    public void run(){
        this.sala.entrarSala();
        this.recorrerMuseo();
        this.sala.salirSala();
        
    }
    
        public void recorrerMuseo(){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
