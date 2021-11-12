/*
 * Mauricio Sawicki
 */
package PrimerParcial.CentroHomoterapia;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Centro {

    private Semaphore mutex;
    private Semaphore mutexSillas;
    private Semaphore recepcionista;
    private Semaphore especialistaClinico;
    private Semaphore extractorSangre;
    private Semaphore donante;
    private int sillasLibres;

    public Centro(int totalSillas) {
        sillasLibres = totalSillas;
        recepcionista = new Semaphore(0);
        especialistaClinico = new Semaphore(1, true);
        extractorSangre = new Semaphore(1, true);
        donante = new Semaphore(0);
        mutex = new Semaphore(1);
        mutexSillas = new Semaphore(1, true);
    }

    public void atenderLlamado() throws InterruptedException {
        recepcionista.acquire();
        System.out.println("La recepcionista ha atendido la llamada!");
    }

    public void terminarLlamado() throws InterruptedException {
        System.out.println("La recepcionista finalizó la llamada.");
        donante.release(); // Libero a los donantes
        mutex.release();
    }

    public void llamarAlCentro(String nombre) throws InterruptedException {
        mutex.acquire();
        recepcionista.release();
        donante.acquire();
    }

    public boolean entrar(String nombre) throws InterruptedException {
        boolean pudoEntrar = false;
        mutexSillas.acquire();
        if (sillasLibres > 0) {
            pudoEntrar = true;
            sillasLibres--;
            System.out.println(nombre + ": encontré una silla libre! Aguardo sentado.");
        } else {
            System.out.println(nombre + ": No hay sillas libres, regreso luego..");
        }
        mutexSillas.release();
        return pudoEntrar;
    }

    public void entrevistaMedica(String nombre) throws InterruptedException {
        especialistaClinico.acquire();
        mutexSillas.acquire();
        sillasLibres++;
        mutexSillas.release();
        System.out.println("El especialista clinico llama a " + nombre + " para realizar chequearlo.");
        System.out.println(nombre + " ha pasado el chequeo médico.");
        
    }

    public void extraccionSangre(String nombre) throws InterruptedException {
        especialistaClinico.release();
        extractorSangre.acquire();
        System.out.println("El extractor de sangre llama a " + nombre + " para realizarle la extracción.");
        System.out.println(nombre + " se está realizando la extracción de sangre..");
    }

    public void certificadoDonacion(String nombre) throws InterruptedException {
        extractorSangre.release();
        System.out.println(nombre + " ha recibido un certificado de donación.");
    }

}
