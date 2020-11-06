/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.TrenTuristico;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
//Recurso compartido
public class Tren {

    private Semaphore asientos;

    //Semaforos del vendedor
    private Semaphore vendedor = new Semaphore(0, true);
    private Semaphore ticket = new Semaphore(0, true);

    //Mutex para que el vendedor atienda de a uno a la vez
    private Semaphore mutex = new Semaphore(1, true);

    //Control
    private Semaphore control = new Semaphore(0, true);
    private Semaphore viaje = new Semaphore(0, true);

    //Capacidad
    private int capacidadMaxima, capacidadActual;

    //Constructor del tren con el numero de asientos que se pueden ocupar
    public Tren(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        //Semaforo con capacidadMaxima permisos
        asientos = new Semaphore(capacidadMaxima, true);
    }
    
   
    public void entrar() {
        try {
            //Adquiero el mutex para que los hilos ejecuten atomicamente todas las tareas que estan a continuacion
            mutex.acquire();        
            asientos.acquire();     //
            System.out.println(Thread.currentThread().getName() + " consiguió lugar..");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void comprarTicket() {

        try {
  
            vendedor.release();
            ticket.acquire();
            System.out.println(Thread.currentThread().getName() + " ya tiene su ticket...");
            capacidadActual++;
            
            //Hay 5 asientos en el tren, si se llenan los 5, el control del tren le indica a este que puede arrancar.
            if (this.capacidadActual % 5 == 0) {

                control.release();

            }

            mutex.release(); 
            //Termino el hilo de hacer todas las tareas, suelta el mutex.
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void venderTickets() {

        try {
            vendedor.acquire();

            //Simulacion de tiempo generando el ticket
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " generando el ticket...");
            
            //Sale el ticket
            ticket.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void empezarViaje() {

        try {
            control.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void simularViaje() {

        try {
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(" ------------------------------ arrancó el viaje");
            System.out.println("CHUCUCHUCUCHUCUCHUUUUUUUUUUUUUUUUUUU");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void terminarViaje() {
        //Suelto capacidadMaxima permisos (simula que los pasajeros se bajan del tren)
        viaje.release(capacidadMaxima);
        asientos.release(capacidadMaxima);
        System.out.println( " ------------------------------ Todos los pasajeros se bajaron del tren");

    }
    
    
    public void viajar(){
       
        try {
            viaje.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

}