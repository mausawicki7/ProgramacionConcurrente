/*
 * Mauricio Sawicki
 */
package EjParcialBlancanieves;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Mesa {

    private Semaphore semSillas;
    private Semaphore servirComida = new Semaphore(0);
    private Semaphore comer = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private int enanosSentados = 0;

    public Mesa(int cantSillas) {
        semSillas = new Semaphore(cantSillas);
    }

    public boolean entrarAComer() throws InterruptedException {
        boolean puedeComer = false;
        if (this.semSillas.tryAcquire()) {
            puedeComer = true;
            mutex.acquire();
            enanosSentados++;
            mutex.release();
            System.out.println(Thread.currentThread().getName() + " se sentó en una silla..");
            servirComida.release();
            comer.acquire();

        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo sentarse, estaban todas las sillas ocupadas..");
        }
        return puedeComer;
    }

    public void terminarDeComer() throws InterruptedException {
        this.semSillas.release();
        mutex.acquire();
        enanosSentados--;
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " terminó de comer.");

    }

    public void esperarPedido() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " está paseando..");
        servirComida.acquire();

    }

    public void servirComida() {
        System.out.println(Thread.currentThread().getName() + " sirvió la comida..");
        comer.release();
    }

}
