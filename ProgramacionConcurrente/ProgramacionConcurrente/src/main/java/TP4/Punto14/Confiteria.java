/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto14;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Confiteria {

    Semaphore mutexSillas, mutexMozo, mutexCocinero, semMozo, semCocinero, semComida, semBebida, semComidaYBebida;
    boolean bebida, comida;
    int sillasLibres = 2;
    String miNombre;

    public Confiteria(String nombreConf, int cantSillas) {

        this.mutexSillas = new Semaphore(1, true);
        this.mutexMozo = new Semaphore(1, true);
        this.mutexCocinero = new Semaphore(1, true);
        this.semMozo = new Semaphore(0);
        this.semBebida = new Semaphore(0);
        this.semComida = new Semaphore(0);
        this.semComidaYBebida = new Semaphore(0);
        this.semCocinero = new Semaphore(0);
        this.miNombre = nombreConf;
        this.sillasLibres = cantSillas;

    }

    //El empleado entra a la Confiteria y pide la carta
    //Si no consigue una silla para sentarse se va.
    public boolean entrar(String nombreEmpleado) {
        boolean pudeEntrar = false;
        System.out.println("------------- soy " + nombreEmpleado + " estoy entrando.");
        try {
            // SECCION CRITICA
            // Tenemos el mutexSillas para que cada hilo haga la siguiente verificacion secuencialmente.
            mutexSillas.acquire();
            //El cliente verifica si hay sillas libres
            if (sillasLibres <= 2 && sillasLibres > 0) {
                //Ocupa una silla
                sillasLibres--;
                pudeEntrar = true;
                System.out.println("Hola soy " + nombreEmpleado + " quisiera ver la carta por favor.");
                System.out.println("Soy " + nombreEmpleado + " estoy mirando la carta.");
            } else {
                System.out.println("Soy " + nombreEmpleado + " no encontre sillas disponibles para sentarme. Vuelvo luego");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        mutexSillas.release();
        return (pudeEntrar);
    }

//    public void viendoLaCarta(String nombreEmpleado) {
//        try {
//            System.out.println("Soy " + nombreEmpleado + " estoy mirando la carta");
//            Thread.sleep(1500);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //El empleado le avisa al mozo que quiere una bebida.
    public void pedirBebida(String nombreEmpleado) {
        try {
            System.out.println("Soy " + nombreEmpleado + " quisiera pedir una bebida. Voy a llamar al mozo.");
            mutexMozo.acquire();
            semMozo.release();
            semBebida.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutexMozo.release();
    }

    //El empleado le avisa al mozo que quiere pedir comida.
    public void pedirComida(String nombreEmpleado) {
        try {
            System.out.println("Soy " + nombreEmpleado + " quisiera pedir comida. Voy a llamar al cocinero.");
            mutexCocinero.acquire();
            semCocinero.release();
            semComida.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        mutexCocinero.release();
    }

    //Esta opcion se ejecuta cuando el Empleado desea beber y comer.
    public void pedirComidaYBebida(String nombreEmpleado) {
        System.out.println("Soy " + nombreEmpleado + " quisiera pedir bebida y comida. Voy a llamar al mozo y luego al cocinero.");
        this.pedirBebida(nombreEmpleado);
        this.pedirComida(nombreEmpleado);
    }
       
    //Tiempo libre del mozo.
    public void mozoEsperaEmpleado(String nombreMozo) {
        System.out.println("Soy el mozo " + nombreMozo + " me voy a inventar nuevas versiones de pollo.");
        try {
            semMozo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Tiempo libre del cocinero.
    public void cocineroEsperaEmpleado(String nombreCocinero) {
        System.out.println("Soy el cocinero " + nombreCocinero + " me voy a limpiar la cocina y a inventar nuevas recetas!");
        try {
            semCocinero.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarAtencionMozo() {
        semBebida.release();
    }

    public void terminarAtencionCocinero() {
        semComida.release();
    }

    //Cuando el empleado se va, desocupa una silla dejando que otro hilo la ocupe.
    public void salir(String nombre) {
        try {
            mutexSillas.acquire();
            sillasLibres++;
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mutexSillas.release();
    }

}
