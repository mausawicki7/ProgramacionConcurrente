/*
 * Mauricio Sawicki
 */
package SawickiFAI2256;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Pista {
    Semaphore semAvionAterriza, semAvionDespega, mutexPista;
    int avionesEnTierra = 0;

    public Pista(String nombreTorre) {

        this.mutexPista = new Semaphore(1, true);    //Semaforo para logra la exclusion mutua en la pista
        this.semAvionDespega = new Semaphore(0, true);
        this.semAvionAterriza = new Semaphore(1, true);

    }

    public void empezarAterrizaje(String nombreAvion) throws InterruptedException {
        mutexPista.acquire();
        System.out.println("------------- soy " + nombreAvion + " voy a aterrizar.");
        semAvionAterriza.acquire();
        avionesEnTierra++;
        mutexPista.release();
    }

    public void terminarAterrizaje(String nombreAvion) throws InterruptedException {
        semAvionAterriza.release();
    }
    
    
    public void controlarAterrizajes(){
        
    }
    
    
    
    
    
    

    public void despegarA(String nombreAvion) throws InterruptedException {
        System.out.println("------------- soy " + nombreAvion + " estoy listo para despegar.");
        try {
            //Pide permiso para usar la pista
            mutexPista.acquire();
            System.out.println("Soy " + nombreAvion + " estoy despegando.");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Libera la pista
        mutexPista.release();
    }

    public boolean despegarB(String nombreAvion) throws InterruptedException {
        boolean puedoDespegar = false;
        System.out.println("------------- soy " + nombreAvion + " estoy listo para despegar.");
        try {
            //Pide permiso para usar la pista
            mutexPista.acquire();
            //Si aterrizaron 10 aviones o más, que despegue un avion.
            if (avionesEnTierra >= 10) {
                System.out.println("Han aterrizado 10 aviones.");
                puedoDespegar = true;
                System.out.println("Soy " + nombreAvion + " estoy despegando.");
            } else {
                System.out.println("Soy " + nombreAvion + " tengo que esperar a que aterricen 10 aviones para poder despegar. Aviones en tierra: " + avionesEnTierra);
            }
            avionesEnTierra = 0;
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Libera la pista
        mutexPista.release();
        return puedoDespegar;
    }
}
