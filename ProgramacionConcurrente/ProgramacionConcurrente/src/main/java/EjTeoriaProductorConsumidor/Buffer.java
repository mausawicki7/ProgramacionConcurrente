 /*
    BUFFER ILIMITADO
 */
package EjTeoriaProductorConsumidor;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Buffer {

    private final Semaphore semConsumidor;
    private final Semaphore semProductor;
    private final Semaphore mutex;
    private final int cantMaxima;
    private int cantActual = 0;

    public Buffer(String nomb, int cantStock) { 
        cantMaxima = cantStock;
        semConsumidor = new Semaphore(0);
        semProductor = new Semaphore(this.cantMaxima);
        mutex = new Semaphore(1);
    }

    public void agregarProducto() {
        try {
            this.semProductor.acquire();

            mutex.acquire();

            System.out.println("El productor esta agregando un producto...");
            Thread.sleep(1000);
            this.cantActual++;
            System.out.println("El productor agrego un producto. Capacidad actual : " + this.cantActual);

            if (this.cantActual == this.cantMaxima) {
                this.semConsumidor.release(this.cantMaxima);
            }

            mutex.release();

        } catch (Exception e) {
        }
    }

    public void consumirProducto() {
        try {
            this.semConsumidor.acquire();
            System.out.println(Thread.currentThread().getName() + " esta consumiendo un producto...");
            Thread.sleep((int) (Math.random() * 3000));
            System.out.println(Thread.currentThread().getName() + " termino de consumir.");

            mutex.acquire();
            this.cantActual--;

            if (this.cantActual == 0) {
                this.semProductor.release(this.cantMaxima);
            }

            mutex.release();

        } catch (Exception e) {
        }

    }
}