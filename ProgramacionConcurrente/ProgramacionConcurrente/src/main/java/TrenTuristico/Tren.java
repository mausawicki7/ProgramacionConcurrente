/*
 * Mauricio Sawicki
 */
package TrenTuristico;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Tren {

    private Semaphore vendedor, control, mutex, subir, bajar, ticket, viaje;
    private int cantActual = 0;
    private int capacidadMax;
    private boolean enViaje = false;

    public Tren(int capacidadMax) {
        this.vendedor = new Semaphore(0, true);
        this.control = new Semaphore(0, true);
        this.ticket = new Semaphore(0, true);
        this.viaje = new Semaphore(0, true);
        this.subir = new Semaphore(1, true);
        this.bajar = new Semaphore(0, true);
        this.mutex = new Semaphore(1, true);
        this.capacidadMax = capacidadMax;
    }

    //Metodo del hilo vendedor
    public void venderTickets() throws InterruptedException {
        System.out.println("Esperando pasajeros..");
        vendedor.acquire();
    }

    public void entregarTicket() {
        ticket.release();//Se genera el ticket para el pasajero que lo pide
    }

    //Metodos del hilo pasajero
    public void comprarTicket(String nombre) throws InterruptedException {
        mutex.acquire();
        vendedor.release();
        ticket.acquire();
        System.out.println(nombre + " ya tiene su ticket.");
        mutex.release();
    }

    public void subirseTren(String nombre) throws InterruptedException {
        subir.acquire();

        System.out.println(nombre + " se subió al tren.");
        cantActual++;

        if (cantActual == capacidadMax) {
            control.release(); //Le avisa al control que el tren está listo para arrancar porque está lleno
        } else {
            subir.release(); //Señal para que se suba otro pax
        }

    }

    public void bajarseTren(String nombre) throws InterruptedException {
        bajar.acquire();
        System.out.println(nombre + " se bajó del tren.");
        cantActual--;
        
        if (cantActual == 0) {
            control.release();
        } else {
            bajar.release();
        }
    }

    public void viajar(String nombre) throws InterruptedException {
        viaje.acquire();
    }

    //Metodos del hilo controlTren
    public void empezarViaje() throws InterruptedException {
        control.acquire();
    }

    public void terminarViaje() throws InterruptedException {
        viaje.release();
        bajar.release();
        System.out.println("Se termino el viaje.");

    }

}
