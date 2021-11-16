package TP3.Punto2;

/**
 *
 * @author mausa
 */
public class Jugador {
    private int vida = 10;
    
  //public synchronized void recibirDaño(int daño){
  //    vida = vida + daño;
  //} 
  
 //   public synchronized void recibirCura(int cura){
 //     vida = vida + cura;
 // }
    
    public synchronized int getVida() {
        return this.vida;
    }

    public synchronized void setVida(int vida, String nombre) {
        System.out.println(nombre + " Modificando vida...");
        this.vida = vida;
        System.out.println("La nueva vida del jugador es: " + vida);
    }

}
  
