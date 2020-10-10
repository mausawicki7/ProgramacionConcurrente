/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjTeoriaBarberoDormilon;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Barberia {

    //Lo mas recomendable es que la sincronizacion la haga el recurso compartido
    //Como en este caso al Barberia es el recurso compartido entre el Barbero y el Cliente y aqui tengo los semaforos
    Semaphore semSillon;
    Semaphore semBarbero;
    Semaphore semCliente;
    Semaphore mutexSillas;
    String miNombre;
    int sillasLibres;

    public Barberia(String nombre, int totalSillas) {
        this.miNombre = nombre;
        this.sillasLibres = totalSillas;
        //Inicializo los semaforos en true para que el barbero le corte el pelo a los clientes que estan esperando para ser atendidos
        //en orden de llegada.

        mutexSillas = new Semaphore(1, true);        //Semaforo para lograr la exclusion mutua
        semSillon = new Semaphore(1, true);
        semBarbero = new Semaphore(0, true);
        semCliente = new Semaphore(0, true);
        System.out.println("Bienvenidos a la barberia 'El barbero dormilon'");
    }

    public boolean entrar(String nombreCliente) {
        boolean pudeEntrar = false;
        System.out.println("------------- soy " + nombreCliente + " estoy entrando.");
        try {
            // SECCION CRITICA
            mutexSillas.acquire();
            //El cliente verifica si hay sillas libres
            if (sillasLibres > 0) {
                //Ocupa una silla
                sillasLibres--;
                pudeEntrar = true;
            }else{
                System.out.println("Soy "+nombreCliente+" no encontre sillas disponibles para sentarme. ME VOY!");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }

        mutexSillas.release();
        return (pudeEntrar);
    }

    public void solicitarCorte(String nombreCliente) {
        try {
            // SECCION CRITICA
            semSillon.acquire();
            mutexSillas.acquire(); 
            sillasLibres++;
            mutexSillas.release();
            
            
            System.out.println("Soy " + nombreCliente + " necesito un corte de pelo. Voy a despertar al barbero.");
            semBarbero.release();
            semCliente.acquire();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void esperarCliente(String nombreBarbero) {
        System.out.println("Me voy a descansar un rato!");
        try {
            semBarbero.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void terminarAtencion() {
        semCliente.release();
    }

    public void salir(String nombre) {
        semSillon.release();
    }

}
