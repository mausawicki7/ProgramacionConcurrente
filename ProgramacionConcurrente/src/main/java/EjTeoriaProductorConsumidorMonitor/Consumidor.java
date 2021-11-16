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
public class Consumidor implements Runnable {
    
        private Buffer unBuffer;

    public Consumidor(Buffer unBuffer) {
        this.unBuffer = unBuffer;
    }

    public void run() {
        unBuffer.consumir();
    }
}
