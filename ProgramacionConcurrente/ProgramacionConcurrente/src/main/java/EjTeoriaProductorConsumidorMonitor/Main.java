/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjTeoriaProductorConsumidorMonitor;

/**
 *
 * @author mausa
 */
public class Main {
     
    public static void main(String[] args) {
        
        int capacidadBuffer = 5;
        
        Buffer b = new Buffer(capacidadBuffer);
        
        Productor p = new Productor(b);
        Thread productor = new Thread(p, "Productor");
        productor.start();
        
        
        for (int i = 1; i <= 11 ; i++) {
            Consumidor c = new Consumidor(b);
            Thread consumidor = new Thread(c, "Consumidor " + i);
            consumidor.start();
            
            
        }
        
        
        
        
    }
}
