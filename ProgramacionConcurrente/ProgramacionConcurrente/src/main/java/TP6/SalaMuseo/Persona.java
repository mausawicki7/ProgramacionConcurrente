/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

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
        this.sala.recorrerMuseo();
        this.sala.salirSala();
        
    }
}
