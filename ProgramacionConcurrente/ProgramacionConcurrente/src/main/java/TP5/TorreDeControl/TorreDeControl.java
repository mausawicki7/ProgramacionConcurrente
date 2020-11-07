package TP5.TorreDeControl;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class TorreDeControl {
    
    //Semaforo para que los aviones puedan usar la pista de a uno por vez.
    private Semaphore mutexPista = new Semaphore(1, true); 
    private Semaphore despegar = new Semaphore(0, true);
    private Semaphore aterrizar;
    private int cantAvionesConPrioridad, aterrizajes, aterrizajesRestantes;
    
    public TorreDeControl(int aRestantes, int priority){
        //Semaforo para indicar la prioridad ej: Cada (priority) aviones que aterrizan debe despegar un avión
        this.aterrizar = new Semaphore(priority, true);
        this.aterrizajesRestantes = aRestantes;
        this.cantAvionesConPrioridad = priority;
    }
    
    public void solicitarPermisoParaDespegar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() +" solicita permiso para despegar...");
        despegar.acquire();
    }
    
    public void solicitarPermisoParaAterrizar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() +" solicita permiso para aterrizar...");
        aterrizar.acquire();      
    }
    
    public void usarPista() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() +" ha obtenido el permiso para usar la pista!");
        mutexPista.acquire();
    }  
    
    public void despegar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() +" está despegando.");
        Thread.sleep((int) (Math.random() * 1000));
        System.out.println(Thread.currentThread().getName() +" ha despegado.");
        
        //Si ya no quedan mas aviones por aterrizar, entonces dale el permiso para despegar a los que están ready.
        if (aterrizajesRestantes == 0){
            despegar.release();
        //Si quedan aviones por aterrizar, entonces que aterricen todos los que faltan.
        } else {
            aterrizar.release(cantAvionesConPrioridad);
        }
        //Los aviones terminan de usar la pista
        mutexPista.release();
    }
    
    public void aterrizar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() +" está aterrizando.");
        Thread.sleep((int) (Math.random() * 1000));
        System.out.println(Thread.currentThread().getName() +" ha aterrizado y estaciono fuera de la pista.");
        
        this.aterrizajes++;
        this.aterrizajesRestantes--;
        
        //Si ya no quedan mas aviones con prioridad de aterrizaje o si ya no quedan mas aviones por aterrizar
        //Dale permiso para despegar a los que estan en tierra
        if (aterrizajes % cantAvionesConPrioridad == 0 || aterrizajesRestantes == 0){
            despegar.release();
        }
        
        //Los aviones terminan de usar la pista
        mutexPista.release();
    }
    
    
}
