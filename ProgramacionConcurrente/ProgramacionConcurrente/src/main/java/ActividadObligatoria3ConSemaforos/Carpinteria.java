/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3;

import java.util.concurrent.Semaphore;

public class Carpinteria {

    private Semaphore semPonerBuffer1, semPonerBuffer2, semPonerBuffer3, semFabricarParte1, semFabricarParte2, semFabricarParte3, semEnsamblaje, semCantMuebles;

    public Carpinteria(int cantidadMuebles) {
        semCantMuebles = new Semaphore(cantidadMuebles);
        semPonerBuffer1 = new Semaphore(1);
        semPonerBuffer2 = new Semaphore(1);
        semPonerBuffer3 = new Semaphore(1);
        semEnsamblaje = new Semaphore(3);
    }

    public void fabricarParte1(String nombre) throws InterruptedException {
        semFabricarParte1.acquire();
        System.out.println(nombre + " comenzó a fabricar la Parte 1");
    }

    public void terminarParte1(String nombre) throws InterruptedException {
        semFabricarParte1.release();
        System.out.println(nombre + " comenzó a fabricar la Parte 1");
    }

    public void fabricarParte2(String nombre) {

    }

    public void fabricarParte3(String nombre) {

    }
}
