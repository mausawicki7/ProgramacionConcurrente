/*
 * Mauricio Sawicki
 */
package Sawicki_FAI2256.Ejercicio1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mausa
 */
public class Mirador {

    private Semaphore semAtencion;
    private Semaphore semEntrada;
    private Semaphore semT1;
    private Semaphore semT2;
    private Semaphore semUsoT1;
    private Semaphore semUsoT2;
    private boolean irAT1 = true;

    public Mirador() {
        this.semAtencion = new Semaphore(0);
        this.semEntrada = new Semaphore(0);
        this.semT1 = new Semaphore(0);
        this.semT2 = new Semaphore(0);
        this.semUsoT1 = new Semaphore(1);
        this.semUsoT2 = new Semaphore(1);
    }

    public void pedirAtencion() {
        System.out.println(Thread.currentThread().getName() + " entro al parque.");
        this.semAtencion.release();
        try {
            this.semEntrada.acquire();
        } catch (Exception e) {
        }
    }

    public void usarTobogan() {
        if (this.semT1.tryAcquire()) {
            try {
                this.semUsoT1.acquire();
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + " se esta tirando por el TOBOGAN 1...");

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }

            System.out.println(Thread.currentThread().getName() + " se bajo del TOBOGAN 1...");
            this.semUsoT1.release();
        } else {
            try {
                this.semT2.acquire();
                this.semUsoT2.acquire();
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + " se esta tirando por el TOBOGAN 2...");

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }

            System.out.println(Thread.currentThread().getName() + " se bajo del TOBOGAN 2...");
            this.semUsoT2.release();
        }
    }

    public void esperarVisitante() {
        System.out.println(Thread.currentThread().getName() + " esta esperando visitantes...");
        try {
            this.semAtencion.acquire();
        } catch (Exception e) {
        }
    }

    public void despacharVisitante() {
        if (irAT1) {
            this.semT1.release();
        } else {
            this.semT2.release();
        }

        this.irAT1 = !this.irAT1;

        this.semEntrada.release();
    }

}

