/*
 * Mauricio Sawicki
 */
package TP6.Pasteleria;

/**
 *
 * @author mausa
 */
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pasteleria {

    private boolean hayCaja = true;

    private int pesoActual = 0;
    private int pesoMaximo;

    private int indice = 0;
    private int capacidadActual = 0;
    private int capacidadMostrador;
    private int[] pasteles;

    private Lock mutex = new ReentrantLock(true);
    private Condition esperaCaja = mutex.newCondition();
    private Condition esperaBrazo = mutex.newCondition();
    private Condition esperaHorno = mutex.newCondition();
    private Condition esperaPastel = mutex.newCondition();

    public Pasteleria(int pesoMaximo, int capacidadMostrador) {
        this.pesoMaximo = pesoMaximo;
        pasteles = new int[capacidadMostrador];
        this.capacidadMostrador = capacidadMostrador;
    }

    // Metodo de empaquetador 
    public int tomarPastel() {

        mutex.lock();

        int peso = 0;

        try {
            //Si no hay pasteles espera
            while (capacidadActual == 0) {

                esperaPastel.await();

            }
          
            //Toma un pastel con el peso
            peso = pasteles[indice - 1];
            // Lo "elimina" y baja la cantidad actual
            pasteles[indice - 1] = 0;
            capacidadActual--;
            indice--;

            //Avisa que hay espacio para un producto más
            esperaHorno.signalAll();

        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            mutex.unlock();

        }

        return peso;

    }

    public void soltarPastel(int peso) {

        mutex.lock();

        try {
  
            //Si todavía no hay una caja posicionada o si se supera el peso debe esperar
            while (peso + pesoActual > pesoMaximo) {
                
                //Le avisa al brazo que la caja ya está cargada para que la lleve
                hayCaja = false;
                esperaBrazo.signal();
                
                //Espera a que haya una nueva caja vacía
                esperaCaja.await();

            }

            //Si no carga la caja
            pesoActual += peso;
            System.out.println(Thread.currentThread().getName() + " poniendo un pastel de " + peso + "kg en la caja");
            System.out.println("Carga actual de la caja: " + pesoActual);

        } catch (InterruptedException e) {
        } finally {

            mutex.unlock();

        }

    }

    public void retirarCaja() {
        mutex.lock();

        try {
            //Mientras la caja esté siendo utilizada espera
            while (hayCaja) {

                esperaBrazo.await();
            }

            System.out.println(Thread.currentThread().getName() + " retirando la caja llena");
            //Repone la caja vacía (para que no entren más brazos si los hay)
            hayCaja = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            mutex.unlock();

        }

    }

    public void reponerCaja() {

        mutex.lock();
        try {
            
            //Restaura las variables
            System.out.println(Thread.currentThread().getName() + " dejó una caja nueva");
            pesoActual = 0;

            //Aviso al/los empaquetadores que pueden seguir
            esperaCaja.signal();
        } finally {
            mutex.unlock();
        }

    }

    public void dejarPastel(int pesoPastel) {

        mutex.lock();

        try {

            // Para saber si el arreglo está lleno y esperar
            while (capacidadActual >= capacidadMostrador) {

                esperaHorno.await();

            }

            // Se guarda el peso del pastel y se incrementa el indice
            pasteles[indice] = pesoPastel;
            indice++;
            capacidadActual++;

            System.out.println(Thread.currentThread().getName() + " generó un pastel. Mostrador: " + capacidadActual + " pasteles");

            //Avisa a los brazos que ya pueden agarrar un pastel
            esperaPastel.signalAll();

        } catch (InterruptedException ex) {
            Logger.getLogger(Pasteleria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            mutex.unlock();

        }

    }

}
