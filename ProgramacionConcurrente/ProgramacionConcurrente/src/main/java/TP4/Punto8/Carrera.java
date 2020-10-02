/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Carrera {

    public static void main(String[] args) {
        Testigo t = new Testigo();
        int cantCorredores = 4;
        Corredor[] arrayCorredores = new Corredor[cantCorredores];
        Thread[] hilos = new Thread[cantCorredores];
        int cant = cantCorredores - 1;
        double tiempoInicial = System.currentTimeMillis();
        
        for (int i = 0; i <= cant; i++) {
            hilos[i] = new Thread(new Corredor(i + 1, t), "Corredor " + (int) (i + 1));
        }

        for (int i = 0; i <= cant; i++) {
            hilos[i].start();
        }
        
        for (int i = 0; i <= cant; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Carrera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        double tiempoFinal = System.currentTimeMillis();
        System.out.println("Tardaron: " + (tiempoFinal - tiempoInicial) / 1000 + " segundos");
    }
}
