/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjParcialPerrosAlaskaMonitores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mausa
 */
public class Comedero {

    private int cantPlatos, platosLlenos, perrosComiendo;
    private Lock mutex = new ReentrantLock();
    private Condition esperaComida = mutex.newCondition();
    private Condition esperaDueño = mutex.newCondition();
    private Condition esperaReponer = mutex.newCondition();
    private boolean avisado = false;

    public Comedero(int cantidad) {
        this.cantPlatos = cantidad;
        //Inician con todos los platos llenos
        this.platosLlenos = cantPlatos;
    }

    public void empezarAComer() throws InterruptedException {
        mutex.lock();
        try {
            while (platosLlenos == 0) {
                if (!avisado) {
                    avisado = true;
                    esperaDueño.signal();
                    System.out.println("No hay comida! " + Thread.currentThread().getName() + " ladra al dueño!!!");
                }
                esperaComida.await();
            }
            platosLlenos--;
            perrosComiendo++;
                        System.out.println("Platos llenos: " + platosLlenos);
            System.out.println(Thread.currentThread().getName() + " está comiendo..");

        } finally {
            mutex.unlock();
        }
    }

    public void terminarDeComer() {
        mutex.lock();
        try {                     
            perrosComiendo--;
            System.out.println(Thread.currentThread().getName() + " terminó de comer");
            if(avisado && perrosComiendo == 0){
                esperaReponer.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void prepararComida() throws InterruptedException {
        mutex.lock();
        try {
            while (platosLlenos == cantPlatos) {     
                System.out.println(Thread.currentThread().getName() + " están todos los platos llenos, aun no tengo que reponerlos.");
                esperaDueño.await();
            }
            
            if(perrosComiendo>0){
               esperaReponer.await(); 
            }
            
            avisado = false;
            System.out.println(Thread.currentThread().getName() + " llenó todos los platos con comida..");
            platosLlenos = cantPlatos;
            esperaComida.signalAll();
        } finally {
            mutex.unlock();
        }
    }

}
