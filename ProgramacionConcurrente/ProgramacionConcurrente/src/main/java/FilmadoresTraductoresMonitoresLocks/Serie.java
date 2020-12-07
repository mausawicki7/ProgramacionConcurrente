/*
 * Mauricio Sawicki
 */
package FilmadoresTraductoresMonitoresLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import EstructurasDinamicas.ColaDinamica;
import EstructurasLineales.Lista;

/**
 *
 * @author mausa
 */
public class Serie {

    private int cantCapitulosFilmados;
    private int cantCapitulosTraducidos;
    private Lock mutex = new ReentrantLock(true);
    private Condition traductores = mutex.newCondition();
    private Condition esperaVerCastellano = mutex.newCondition();
    private Condition esperaVerIngles = mutex.newCondition();
    private ColaDinamica cola = new ColaDinamica();
    private Lista listaCastellano = new Lista();
    private Lista listaIngles = new Lista();

    public Serie() {
        cantCapitulosFilmados = 0;
        cantCapitulosTraducidos = 0;
    }

    public void empezarFilmacion() {
        mutex.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " empezó a filmar el capítulo " + (cantCapitulosFilmados + 1));
        } finally {
            mutex.unlock();
        }
    }

    public void terminarFilmacion() {
        mutex.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " terminó de filmar el capítulo " + (cantCapitulosFilmados + 1));
            cantCapitulosFilmados++;
            cola.poner(cantCapitulosFilmados);
            traductores.signal();
            listaCastellano.insertar(cantCapitulosFilmados, cantCapitulosFilmados);
            
            //Despierto a todos los socios
            esperaVerCastellano.signalAll();
        } finally {
            mutex.unlock();
        }
    }

    public int empezarTraduccion() throws InterruptedException {
        int capATraducir;
        mutex.lock();
        try {
            while (cantCapitulosFilmados == cantCapitulosTraducidos) {
                System.out.println(Thread.currentThread().getName() + " estoy esperando para empezar a traducir..");
                traductores.await();
            }
            capATraducir = (int) cola.obtenerFrente();
            cola.sacar();
            System.out.println(Thread.currentThread().getName() + " está traduciendo al inglés el capítulo " + (capATraducir));
        } finally {
            mutex.unlock();
        }

        return capATraducir;
    }

    public void terminarTraduccion(int capituloTraducido) {
        mutex.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " terminó de traducir el capítulo " + (capituloTraducido));          
            listaIngles.insertar(capituloTraducido, capituloTraducido); 
            cantCapitulosTraducidos++;
            esperaVerIngles.signalAll();
        } finally {
            mutex.unlock();
        }
    }

    public void verCapituloEnCastellano(int capituloPorVer) throws InterruptedException {
        mutex.lock();
        try {
            while (capituloPorVer > cantCapitulosFilmados) {
                System.out.println(Thread.currentThread().getName() + " aún no está el capitulo en castellano.");
                esperaVerCastellano.await();
            }
            System.out.println(Thread.currentThread().getName() + " estoy viendo el capitulo " + (this.listaCastellano.recuperar(capituloPorVer)) + " en castellano.");
        } finally {
            mutex.unlock();
        }
    }

    public void verCapituloEnIngles(int capituloPorVer) throws InterruptedException {
        mutex.lock();
        try {
            while (capituloPorVer > cantCapitulosTraducidos) {
                System.out.println(Thread.currentThread().getName() + " aún no está el capitulo en inglés.");
                esperaVerIngles.await();
            }
            System.out.println(Thread.currentThread().getName() + " estoy viendo el capitulo " + (this.listaIngles.recuperar(capituloPorVer)) + " en inglés.");
        } finally {
            mutex.unlock();
        }
    }
}
