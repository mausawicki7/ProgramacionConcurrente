/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto13;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Confiteria {

    Semaphore semSilla;
    Semaphore semMozo;
    Semaphore semEmpleado;
    Semaphore semSalida;
    Semaphore mutexSillas;
    int sillasLibres = 1;
    String miNombre;

    public Confiteria(String nombreConf) {
        this.semSilla = new Semaphore(1);
        this.mutexSillas = new Semaphore(1);
        this.semMozo = new Semaphore(0);
        this.semEmpleado = new Semaphore(0);
        this.semSalida = new Semaphore(0);
        this.miNombre = nombreConf;
    }
    
    public boolean entrar(String nombreEmpleado){
        boolean pudeEntrar = false;
        System.out.println("------------- soy " + nombreEmpleado + " estoy entrando.");
        try {
            // SECCION CRITICA
            mutexSillas.acquire();
            //El cliente verifica si hay sillas libres
            if (sillasLibres == 1) {
                //Ocupa una silla
                sillasLibres--;
                pudeEntrar = true;
            }else{
                System.out.println("Soy "+nombreEmpleado+" no encontre sillas disponibles para sentarme. ME VOY!");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }

        mutexSillas.release();
        sillasLibres++;
        return (pudeEntrar);
    }
        
       

    public void solicitarAtencion(String nombreEmpleado) {
        try {
            // SECCION CRITICA

            semSilla.acquire();
            System.out.println("Soy " + nombreEmpleado + " me podria traer la carta por favor?");
            semMozo.release();
            semEmpleado.acquire();

        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void esperarEmpleado(String nombreMozo) {
        System.out.println("Soy el mozo"+nombreMozo+" me voy inventar nuevas recetas!");
        try {
            semMozo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void elegirMenu(String nombreEmpleado) {
        try {
            System.out.println("Soy " + nombreEmpleado + " estoy mirando la carta");
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * public void empiezaAComer(){ try {
     *
     * System.out.println("Excelente comida! Esta muy sabrosa."); } catch
     * (InterruptedException ex) {
     * Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
     * } }
     */
    public void terminaDeComer() {
        semEmpleado.release();
        System.out.println("Muy rica la comida, nos vemos!");
    }

    public void salir(String nombreEmpleado) {
        semSilla.release();
    }

}
