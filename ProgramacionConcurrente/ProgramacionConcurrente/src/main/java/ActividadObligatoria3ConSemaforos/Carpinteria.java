/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConSemaforos;

import java.util.concurrent.Semaphore;

public class Carpinteria {

    private Semaphore semPonerBuffer1, semPonerBuffer2, semPonerBuffer3;
    private Semaphore semFabricarParte1, semFabricarParte2, semFabricarParte3, semEnsamblaje, semCantMuebles;

    public Carpinteria(int cantidadMuebles) {
      //  semCantMuebles = new Semaphore(cantidadMuebles);
        semFabricarParte1 = new Semaphore(cantidadMuebles);
        semFabricarParte2 = new Semaphore(cantidadMuebles);
        semFabricarParte3 = new Semaphore(cantidadMuebles);
        semPonerBuffer1 = new Semaphore(1);
        semPonerBuffer2 = new Semaphore(1);
        semPonerBuffer3 = new Semaphore(1);
        semEnsamblaje = new Semaphore(0);
    }
    
    //Métodos de los carpinteros que fabrican piezas
    public void fabricarParte1(String nombre) throws InterruptedException {
        semPonerBuffer1.acquire();
        semFabricarParte1.tryAcquire();
    }

    public void terminarParte1(String nombre) throws InterruptedException {
        semEnsamblaje.release();
    }

    public void fabricarParte2(String nombre) throws InterruptedException {
        semPonerBuffer2.acquire();
        semFabricarParte2.tryAcquire();
    }

    public void terminarParte2(String nombre) throws InterruptedException {
        semEnsamblaje.release();
    }

    public void fabricarParte3(String nombre) throws InterruptedException {
        semPonerBuffer3.acquire();
        semFabricarParte3.tryAcquire();
    }

    public void terminarParte3(String nombre) throws InterruptedException {
        semEnsamblaje.release();
    }
    
    //Métodos de los carpinteros ensambladores
    public void comenzarEnsamblaje() throws InterruptedException{
        semEnsamblaje.tryAcquire(3);  
    }
    
    public void terminarEnsamblaje(){
        semPonerBuffer1.release();
        semPonerBuffer2.release();
        semPonerBuffer3.release();
    }
}
