package TP3.Punto2;

/**
 *
 * @author mausa
 */
public class Curandero implements Runnable {
    private Jugador objetivo;
    private String nombre;
    
    public Curandero(Jugador player, String nom){
        objetivo = player;
        this.nombre = nom;
    }
    
    @Override
    public void run(){
        int vida = objetivo.getVida();
        objetivo.setVida(vida + 3, nombre);
    }
}
