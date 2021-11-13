/*
 * Mauricio Sawicki
 */
package EjTeoriaProductorConsumidorMonitor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Buffer {

    private int capacidadMaxima;
    private int capacidadActual;

    public Buffer(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = 0;
    }

    public synchronized void consumir() {

        while (capacidadActual == 0) {
            //Si el buffer tiene capacidadActual en 0 quiere decir que no hay productos, está vacío.
            System.out.println(Thread.currentThread().getName() + " tiene que esperar, buffer vacío");
            try {
            //Por lo tanto el hilo Consumidor debe esperar a que hayan productos para consumir  
                this.wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //Cuando hay productos para consumir el hilo consume 1 y avisa
        capacidadActual--;
        this.notifyAll();

        System.out.println(Thread.currentThread().getName() + " consumió un producto, capacidad actual: " + capacidadActual);

    }

    public synchronized void producir() {

        while (capacidadActual == capacidadMaxima) {
            try {
                System.out.println(Thread.currentThread().getName() + " esperando, buffer lleno");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        capacidadActual++;
        System.out.println(Thread.currentThread().getName() + " produjo un elemento, capacidad actual: " + capacidadActual);
        this.notifyAll();

    }
}
