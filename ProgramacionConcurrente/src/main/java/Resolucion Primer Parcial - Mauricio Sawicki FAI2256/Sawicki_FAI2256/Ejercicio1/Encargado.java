/*
 * Mauricio Sawicki
 */
package Sawicki_FAI2256.Ejercicio1;

/**
 *
 * @author mausa
 */
public class Encargado implements Runnable {

    private Mirador mirador;

    public Encargado(Mirador mi) {
        this.mirador = mi;
    }

    public void run() {
        while (true) {
            this.mirador.esperarVisitante();
            this.atenderVisitante();
            this.mirador.despacharVisitante();
        }
    }

    private void atenderVisitante() {
        System.out.println(Thread.currentThread().getName() + " esta atendiendo a un visitante...");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " termino de atender.");
    }

}
