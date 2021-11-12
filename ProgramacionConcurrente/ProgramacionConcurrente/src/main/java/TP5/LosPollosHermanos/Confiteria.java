/*
 * Mauricio Sawicki
 */
package TP5.LosPollosHermanos;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author mausa
 */
public class Confiteria {

    Lock mozo = new ReentrantLock();
    Lock comida = new ReentrantLock();
    Lock empleado = new ReentrantLock();
    Lock silla = new ReentrantLock();
    

    public synchronized boolean entrar(String nombre) {
        boolean pudeEntrar = false;
        System.out.println("------------- soy " + nombre + " estoy entrando.");
        System.out.println("Soy " + nombre + " encontré la silla libre, me voy a sentar.");
        pudeEntrar = true;
        System.out.println(nombre + ": Mozo!! Me trae la carta? Quiero comer.");
        mozo.lock();
        return (pudeEntrar);
    }

    public void empezarAComer(String nombre) throws InterruptedException {
        comida.lock();
    }

    public void terminarDeComer() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": termine de comer.");
        mozo.unlock();
    }

    public void esperarEmpleado() {
        System.out.println("Mozo: Voy a inventar nuevas recetas!");
        mozo.lock();
    }
    
    public void servirComida(){
        comida.unlock();
    }

    public void terminarAtencion() {
        empleado.unlock();
    }

    public synchronized void salir() {
        silla.unlock();
    }
}
