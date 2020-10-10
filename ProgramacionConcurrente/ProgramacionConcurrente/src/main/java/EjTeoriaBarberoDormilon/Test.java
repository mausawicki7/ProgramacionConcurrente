/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        Barbero unBarbero = new Barbero("Ricky Fort", unaBarberia);
        
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
