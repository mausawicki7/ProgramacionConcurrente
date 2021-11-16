package TP3.Punto2;

/**
 *
 * @author mausa
 */
public class Orco implements Runnable {
    private Jugador objetivo;
    private String nombre;
    
    public Orco(Jugador player, String nom){
        objetivo = player;
        this.nombre = nom;
    }
    
    public void run(){
        int vida = objetivo.getVida();
        objetivo.setVida(vida - 3, nombre);
    }
}
