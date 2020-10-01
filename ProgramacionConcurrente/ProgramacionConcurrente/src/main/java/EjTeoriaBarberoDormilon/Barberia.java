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
     //Como en este caso al Barberia es el recurso compartido y aqui tengo los semaforos

    Semaphore semSillon;
    Semaphore semBarbero;
    Semaphore semCliente;
    String miNombre;

    public Barberia(String nombre) {
        miNombre = nombre;
        //Inicializo los semaforos en true para que el barbero le corte el pelo a los clientes que estan esperando para ser atendidos
        //en orden de llegada.
        semSillon = new Semaphore(1, true);
        semBarbero = new Semaphore(0, true);
        semCliente = new Semaphore(0, true);
        System.out.println("Bienvenidos a la barberia 'El barbero dormilon'");
    }

    public boolean entrar(String nombreCliente) {
        System.out.println("------------- soy " + nombreCliente + " estoy entrando.");
        return (semSillon.tryAcquire());
    }

    public void solicitarCorte(String nombreCliente) {
        System.out.println("Soy " + nombreCliente + " necesito un corte de pelo.");
        semBarbero.release();

        try {
            semCliente.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void esperarCliente(String nombreBarbero) {
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
