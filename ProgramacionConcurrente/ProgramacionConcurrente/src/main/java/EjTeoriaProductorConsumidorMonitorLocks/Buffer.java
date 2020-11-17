/*
 * Mauricio Sawicki
 */
package EjTeoriaProductorConsumidorMonitorLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mausa
 */
public class Buffer {

    private int cantidad;
    private int tamaño;
    private final Lock mutex = new ReentrantLock();

    //Para sincronizar a los productores
    private final Condition productores;

    //Para sincronizar a los consumidores
    private final Condition consumidores;

    public Buffer(int tam) {
        this.cantidad = 0;
        this.tamaño = tam;
        this.productores = mutex.newCondition();
        this.consumidores = mutex.newCondition();
    }

    public void producir() throws InterruptedException {
        mutex.lock();
        while (cantidad == tamaño) {
            System.out.println(Thread.currentThread().getName() + " no puedo agregar mas productos, buffer lleno.");
            productores.await(); //Espera bloqueao
        }
        cantidad++;
        System.out.println(Thread.currentThread().getName() +" agregando productos..");
        consumidores.signalAll();
        mutex.unlock();
    }

    public void consumir() throws InterruptedException {
        mutex.lock();
        while (cantidad == 0) {
            System.out.println(Thread.currentThread().getName() + " no puedo consumir, buffer vacío.");
            consumidores.await(); //Espera bloqueado
        }
        cantidad--;
        System.out.println(Thread.currentThread().getName() +" consumiendo un producto..");
        productores.signalAll();
        mutex.unlock();
    }

}
