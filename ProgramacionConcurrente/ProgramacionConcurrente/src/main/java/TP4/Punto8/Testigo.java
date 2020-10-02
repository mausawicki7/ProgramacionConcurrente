/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Testigo {

    private Semaphore sem1 = new Semaphore(1);
    private Semaphore sem2 = new Semaphore(0);
    private Semaphore sem3 = new Semaphore(0);
    private Semaphore sem4 = new Semaphore(0);

    public Testigo() {

    }

    public void aCorrer1(double tiempo) {
        double tiempoInicial = 0;
        try {
            sem1.acquire();
            tiempoInicial = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " Comienza a correr!! ");
        try {
            Thread.sleep((long) (tiempo * 800));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " termina su recorrido."
                + " Tardó: " + (System.currentTimeMillis() - tiempoInicial) / 1000 + " segundos");
        System.out.println("Le da el testigo al corredor 2.");

        sem2.release();
    }

    public void aCorrer2(double tiempo) {
        double tiempoInicial = 0;
        try {
            sem2.acquire();
            tiempoInicial = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " Comienza a correr!! ");
        try {
            Thread.sleep((long) (tiempo * 800));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " termina su recorrido."
                + " Tardó: " + (System.currentTimeMillis() - tiempoInicial) / 1000 + " segundos");
        System.out.println("Le da el testigo al corredor 3.");

        sem3.release();
    }

    public void aCorrer3(double tiempo) {
        double tiempoInicial = 0;
        try {
            sem3.acquire();
            tiempoInicial = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " Comienza a correr!! ");
        try {
            Thread.sleep((long) (tiempo * 800));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " termina su recorrido."
                + " Tardó: " + (System.currentTimeMillis() - tiempoInicial) / 1000 + " segundos");
        System.out.println("Le da el testigo al corredor 4.");

        sem4.release();
    }
    
        public void aCorrer4(double tiempo) {
        double tiempoInicial = 0;
        try {
            sem4.acquire();
            tiempoInicial = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " Comienza a correr!! ");
        try {
            Thread.sleep((long) (tiempo * 800));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testigo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName() + " termina su recorrido."
                + " Tardó: " + (System.currentTimeMillis() - tiempoInicial) / 1000 + " segundos");
        System.out.println("FINALIZAN LA CARRERA.");

        sem1.release();
    }

}
