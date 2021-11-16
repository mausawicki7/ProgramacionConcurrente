/*
 * Mauricio Sawicki
 */
package PrimerParcial.TrenTuristico;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Tren {

    private Semaphore vendedor, mutex, subir, bajar, ticket, iniciarViaje;
    private int cantActual = 0;
    private int capacidadMax;

    public Tren(int capacidadMax) {
        this.vendedor = new Semaphore(0, true);
        this.ticket = new Semaphore(0, true);
        this.iniciarViaje = new Semaphore(0, true);
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
            iniciarViaje.release(); //El tren está listo para arrancar porque está lleno
        } else {
            subir.release(); //Señal para que se suba otro pax
        }

    }

    public void bajarseTren(String nombre) throws InterruptedException {
        bajar.acquire();
        System.out.println(nombre + " se bajó del tren.");
        cantActual--;
        
        if (cantActual == 0) {
            subir.release();
        } else {
            bajar.release();
        }
    }

    //Metodos del hilo controlTren
    public void empezarViaje() throws InterruptedException {
        iniciarViaje.acquire();
    }

    public void terminarViaje() throws InterruptedException {
        bajar.release();
        System.out.println("Se termino el viaje.");

    }

}
