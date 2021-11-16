/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.ComedorGatosyPerros;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
//Recurso compartido
public class Comedor {
    
    private Semaphore comederos ;
    private Semaphore semGatos;
    private Semaphore semPerros;
    private Semaphore mutex;
    private int platosDisponibles, perrosQueAunNoComen, gatosQueAunNoComen, cuantosComieron;

    public Comedor(int capacidad, int cantGatos, int cantPerros) {
        this.platosDisponibles = capacidad;
        
        //Semaforo para turno de los gatos
        this.semGatos = new Semaphore (capacidad, true);
        
        //Semaforo para turno de los perros
        this.semPerros = new Semaphore (0, true);
        
        //Semaforo con las cantidad de platos
        this.comederos = new Semaphore(capacidad, true);
        
        this.perrosQueAunNoComen = cantPerros;
        this.gatosQueAunNoComen = cantGatos;
    }
    
    public void ingresanPerros() throws InterruptedException{     
        //Es el turno de que coman los perros, adquiero el permiso
        semPerros.acquire();
    }
    
    public void ingresanGatos() throws InterruptedException{
        //Es el turno de que coman los gatos, adquiero el permiso.
        semGatos.acquire();
    }
    
    public void comer(){
        try {
            comederos.acquire();
            //Voy la variable compartida cuantosComieron, cualquier hilo puede editar, por lo tanto es una seccion critica
            //Exclusion mutua
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() +" comiendo..");
            cuantosComieron++;
            Thread.sleep((int) (Math.random() * 1000)); 

        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutex.release();
        System.out.println(Thread.currentThread().getName()+" terminó de comer");
        comederos.release();
    }
    
    

}
