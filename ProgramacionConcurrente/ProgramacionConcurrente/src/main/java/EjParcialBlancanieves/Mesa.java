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
    private Semaphore semTerminarPaseo = new Semaphore(0);
    private int enanosSentados = 0;
    private boolean paseando = false;

    public Mesa(int cantSillas) {
        semSillas = new Semaphore(cantSillas);
    }

    public boolean entrarAComer() throws InterruptedException {
        boolean puedeComer = false;
        if (this.semSillas.tryAcquire()) {
            puedeComer = true;
            mutex.acquire();
            enanosSentados++;

            System.out.println(Thread.currentThread().getName() + " se sentó en una silla..");

            if (this.paseando) {
                this.semTerminarPaseo.release();
            } else {
                this.servirComida.release();
            }

            mutex.release();

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

        if (this.servirComida.tryAcquire()) {
            this.paseando = false;
        } else {
            System.out.println(Thread.currentThread().getName() + " está paseando..");
            this.paseando = true;
            this.semTerminarPaseo.acquire();
        }

    }

    public void servirComida() {
        System.out.println(Thread.currentThread().getName() + " sirvió la comida..");
        comer.release();
    }

}
