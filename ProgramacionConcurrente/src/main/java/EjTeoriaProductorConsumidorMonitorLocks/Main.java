/*
 * Mauricio Sawicki
 */
package EjTeoriaProductorConsumidorMonitorLocks;

/**
 *
 * @author mausa
 */
public class Main {
     
    public static void main(String[] args) {
        
        int capacidadBuffer = 5;
        
        Buffer b = new Buffer(capacidadBuffer);
        
        Productor p = new Productor(b);
        Thread productor = new Thread(p, "Productor:");
        productor.start();
        
        
        for (int i = 1; i <= 11 ; i++) {
            Consumidor c = new Consumidor(b);
            Thread consumidor = new Thread(c, "Consumidor " + i+":");
            consumidor.start();
            
            
        }
    }
}
