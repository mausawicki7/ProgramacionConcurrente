/*
 * Mauricio Sawicki
 */
package SegundoParcial.PerrosConHambre;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Comedor {

    private Semaphore semPlato, semReponerPlato, mutexPlato;
    private int cantPlatos, platosLlenos;

    public Comedor(int cantidad) {
        this.semPlato = new Semaphore(cantidad, true);
        this.semReponerPlato = new Semaphore(0, true);
        this.mutexPlato = new Semaphore(1, true);
        this.cantPlatos = cantidad;
        this.platosLlenos = cantidad;
    }

    public void empezarComer(String nombre) throws InterruptedException {
        mutexPlato.acquire();
        if (platosLlenos == 0) { //Si no hay mas comida, el perro le avisa al dueño para que reponga
            System.out.println(nombre + ": Los platos están vacíos, GUAU!");
            semReponerPlato.release();
        }
        semPlato.acquire();
        platosLlenos--;
        mutexPlato.release();
    }

    public void terminarComer(String nombre) {
        System.out.println(nombre + ": Terminé de comer!");
    }

    public void empezarReponerPlatos(String nombre) throws InterruptedException {
        semReponerPlato.acquire();
        System.out.println(nombre + ": Empiezo a reponer los platos con comida..");
    }

    public void terminarReponerPlatos(String nombre) {
        platosLlenos = cantPlatos;
        System.out.println(nombre + ": Ya repuse todos los platos! Le aviso a los perros");
        semPlato.release(cantPlatos); //Repone todos los platos
    }
}
