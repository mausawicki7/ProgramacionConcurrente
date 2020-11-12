/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

/**
 *
 * @author mausa
 */
public class Jubilado extends Persona {

    public Jubilado(GestorDeSala sala, String nom) {
        super(sala, nom);
    }
    
    public void run(){
        this.sala.entrarSalaJubilado();
        this.sala.salirSala();
    }
}
