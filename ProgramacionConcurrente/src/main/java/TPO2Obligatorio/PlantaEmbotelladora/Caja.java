/*
 * Mauricio Sawicki
 */
package TPO2Obligatorio.PlantaEmbotelladora;

/**
 *
 * @author mausa
 */
public class Caja {

    private int capacidadMaxima;
    private int capacidadActual;

    public Caja(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = 0;
    }

    public synchronized void empaquetar() throws InterruptedException {
        while (capacidadActual < capacidadMaxima) {
            this.wait(); // mientras que la caja no este llena, espero.
        }

        capacidadActual = 0;
        System.out.println(Thread.currentThread().getName() + ": caja sellada y guardada! Pongo una caja nueva");
        this.notifyAll(); //aviso a los embotelladores
    }

    public synchronized void embotellar() throws InterruptedException {
        while (capacidadActual == capacidadMaxima) {
            System.out.println(Thread.currentThread().getName() + ": esperando, la caja está llena! Capacidad actual: " + capacidadActual);
            this.wait(); // mientras la caja este llena, espero.

        }
        capacidadActual++;
        System.out.println(Thread.currentThread().getName() + " preparó una botella, capacidad actual: " + capacidadActual);
        
        if (capacidadActual == capacidadMaxima) {
            this.notifyAll(); //aviso a los empaquetadores
        }
    }
}
