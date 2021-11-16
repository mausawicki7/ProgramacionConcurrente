package EjTeoriaBarberoDormilon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Test {
    public static void main(String [] args){
        Barberia unaBarberia = new Barberia("El Barbero Dormilon", 5);
        Barbero unBarbero = new Barbero("El navaja", unaBarberia);       
        Thread hiloBarbero = new Thread(unBarbero);
        hiloBarbero.start();
        Thread [] hiloCliente = new Thread[15];
        
        for(int i = 0; i<hiloCliente.length; i++){
            Cliente unCliente = new Cliente("Cliente "+i, unaBarberia);
            hiloCliente[i] = new Thread(unCliente);
            hiloCliente[i].start();
        }
        
        try{
            Thread.sleep((int) (Math.random() * 500));
            
        }catch(InterruptedException ex){
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
