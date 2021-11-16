/*
 * Mauricio Sawicki
 */
package SegundoParcial.BuqueDeAutos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Transbordador {

    private final Semaphore semTransbordador;
    private Semaphore mutex;
    private Semaphore control;
    private Semaphore semBajar;
    private int cantActualAutos, cantMaxAutos;

    public Transbordador(int cantidadAutos) {
        mutex = new Semaphore(1, true);
        control = new Semaphore(0, true);
        semBajar = new Semaphore(1, true);
        this.semTransbordador = new Semaphore(cantidadAutos);
        this.cantMaxAutos = cantidadAutos;
    }

    public void subirAlTransbordador(String nombre) throws InterruptedException {
        //El auto adquiere un permiso para subir al transbordador (hay cantMaxAutos permisos)
        semTransbordador.acquire();
        mutex.acquire();
        //Se sube al transbordador
        cantActualAutos++;
        System.out.println(nombre+" se subió al transbordador.");
        
        //Cuando se llena el transbordador (cuando hay 10 autos) le aviso al Control que puede darle el OK al Transbordador para iniciar su viaje
        if (cantActualAutos == cantMaxAutos) { //Si soy el ultimo auto        
            control.release();
        }
        mutex.release();
    }

    public void bajarDelTransbordador(String nombre) throws InterruptedException {
        semBajar.acquire(); // Bajan de a 1 por vez
        semTransbordador.release();
        semBajar.release();
        System.out.println(nombre+" se bajó del transbordador.");
    }

    public void ir() throws InterruptedException {
        control.acquire();
        System.out.println("Transbordador lleno! Inicia el viaje..");    
    }

    public void volver() throws InterruptedException {
        mutex.acquire();
        cantActualAutos = 0;
        mutex.release();
        System.out.println(" ------------------------------ Todos los autos se bajaron del transbordador.");
        System.out.println("El transbordador pega la vuelta..");
    }

}
