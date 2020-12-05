/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class MontañaRusa {

    private Semaphore semCarro;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore control = new Semaphore(0, true);
    private Semaphore salirCarro = new Semaphore(0, true);
    private int cantActualPasajeros = 0, cantMaxPasajeros;
    private int recorridosActuales = 0, cantMaxRecorridos;

    public MontañaRusa(int cantMaxPasajeros, int cantMaxRecorridos) {
        this.semCarro = new Semaphore(cantMaxPasajeros);
        this.cantMaxPasajeros = cantMaxPasajeros;
        this.cantMaxRecorridos = cantMaxRecorridos;
    }

    public void subir() throws InterruptedException {
        //El pasajero adquiere un permiso para subir al carro (hay cantMaxPasajeros permisos)
        semCarro.acquire();
        mutex.acquire();

        if (recorridosActuales == cantMaxRecorridos) {
            System.out.println("Se terminaron los recorridos.");
        } else {

            System.out.println(Thread.currentThread().getName() + " se subió al carro.");
            cantActualPasajeros++;

            //Cuando se llene el carro, aviso al control de la montaña que puede iniciar la atracción.
            if (this.cantActualPasajeros == cantMaxPasajeros) {
                System.out.println("Ya no pueden subir mas pasajeros.");
                cantActualPasajeros = 0;
                control.release();

            }
            mutex.release();
        }
    }
    
    public void iniciarAtraccion() throws InterruptedException {
        control.acquire();
        recorridosActuales++;
    }

    public void salir() throws InterruptedException{
        salirCarro.acquire();
        System.out.println(Thread.currentThread().getName() + " se bajó del carro.");
    }
    
    public void simularViaje() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " carro lleno, inicia la atracción!");
        Thread.sleep((int) (Math.random() * 1000));
    }

    public void terminarViaje() throws InterruptedException {
        semCarro.release(cantMaxPasajeros);
        salirCarro.release(cantMaxPasajeros);
        System.out.println("Terminó la vuelta..");
        System.out.println(" ------------------------------ Todos los pasajeros se bajaron del carro.");
        //System.out.println("Los pasajeros se fueron a dar una vuelta.");
        //Thread.sleep(2000);
    }

}
