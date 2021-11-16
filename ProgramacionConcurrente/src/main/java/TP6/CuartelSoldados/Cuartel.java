/*
 * Mauricio Sawicki
 */
package TP6.CuartelSoldados;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Cuartel {

    private Semaphore semEntrada;
    private Semaphore semAlmuerzo;
    private Semaphore semPostre;
    private Semaphore semDestapador;

    public Cuartel(int cantMostradores, int cantBandejas, int cantPostres, int cantDestapadores) {
        semEntrada = new Semaphore(cantMostradores);
        semAlmuerzo = new Semaphore(cantBandejas);
        semPostre = new Semaphore(cantPostres);
        semDestapador = new Semaphore(cantDestapadores);
    }

    public void entrar() throws InterruptedException {
        semEntrada.acquire();
        System.out.println(Thread.currentThread().getName() + " entró al cuartel.");
        semAlmuerzo.acquire();
        System.out.println(Thread.currentThread().getName() + " tomó una bandeja.");
    }

    public void tomarBandeja(boolean quierePostre, boolean quiereGaseosa) throws InterruptedException {
        semAlmuerzo.release();
        if (quierePostre) {
            semPostre.acquire();
            this.simularAccion();
            System.out.println(Thread.currentThread().getName() + " agarró un postre.");
            semPostre.release();
        }

        if (quiereGaseosa) {
            semDestapador.acquire();
            System.out.println(Thread.currentThread().getName() + " pudo tomar un destapador y está abriendo la gaseosa.");
            this.simularAccion();
            semDestapador.release();
            System.out.println(Thread.currentThread().getName() + " abrió su gaseosa.");
        }
    }

    public void comer() {
        System.out.println(Thread.currentThread().getName() + " está comiendo.");
        this.simularAccion();
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salió del cuartel.");
        this.semEntrada.release();
    }

    public void simularAccion() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
