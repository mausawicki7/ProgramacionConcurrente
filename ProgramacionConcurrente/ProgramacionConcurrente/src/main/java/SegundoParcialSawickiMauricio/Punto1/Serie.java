/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Serie {

    private int capsPorTraducir;
    private int numCapitulo = 0;
    private String titulo;
    private final Condition traductores;    
    private Lock mutex = new ReentrantLock(true);

    public Serie(String titulo) {
        this.titulo = titulo;
        this.traductores = mutex.newCondition();
    }
    
    // Metodo de filmador 
    public void filmar() {
        mutex.lock();
        try {
            capsPorTraducir++;
            numCapitulo++;
            System.out.println(Thread.currentThread().getName() + " generó el capítulo "+numCapitulo);
            System.out.println("Hay "+capsPorTraducir+" capitulos por traducir");
            //Avisa a los traductores que ya pueden traducir el capitulo.
            traductores.signal();
        } finally {
            mutex.unlock();
        }

    }
    
    // Metodo de traductor 
    public void traducir() {
        mutex.lock();
        try {
            //Si no hay series para traducir espera.
            
            while (capsPorTraducir == 0) {
                System.out.println(Thread.currentThread().getName() + " No hay capítulos para traducir..");
                traductores.await();
            }
                        
            System.out.println(Thread.currentThread().getName() + " Traduciendo el capítulo "+numCapitulo);
            capsPorTraducir--;
            System.out.println(Thread.currentThread().getName() + " Capítulo traducido..");

        } catch (InterruptedException ex) {
            Logger.getLogger(Serie.class.getName()).log(Level.SEVERE, null, ex);
        }

        mutex.unlock();

    }

    public void verSerie(int eleccion) {
        mutex.lock();
        int numCapActual=1;
        try {
            if (eleccion == 1) {
                System.out.println(Thread.currentThread().getName() + " voy a ver la serie en castellano.");
                System.out.println("Viendo capitulo "+numCapActual);
                numCapActual++;
            } else {
                System.out.println(Thread.currentThread().getName() + " voy a ver la serie en inglés.");
                System.out.println("Viendo capitulo "+numCapActual);
                numCapActual++;
            }
        } finally {
            mutex.unlock();
        }
    }

}
