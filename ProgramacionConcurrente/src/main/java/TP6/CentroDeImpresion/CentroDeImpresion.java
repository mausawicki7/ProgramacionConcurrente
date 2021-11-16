/*
 * Mauricio Sawicki
 */
package TP6.CentroDeImpresion;

/**
 *
 * @author mausa
 */
public class CentroDeImpresion {

    private int imprimiendoA = 0, imprimiendoB = 0;
    private int cantImpresorasA, cantImpresorasB;

    public CentroDeImpresion(int cantImpresorasA, int cantImpresorasB) {
        this.cantImpresorasA = cantImpresorasA;
        this.cantImpresorasB = cantImpresorasB;
    }

    public synchronized void imprimirA() throws InterruptedException {

        while (imprimiendoA >= cantImpresorasA) {
            System.out.println("Soy " + Thread.currentThread().getName() + " no puedo imprimir.");
            this.wait();
        }
        imprimiendoA++;
        System.out.println("Soy " + Thread.currentThread().getName() + " estoy imprimiendo en A.");
    }

    public synchronized void imprimirB() throws InterruptedException {
        while (imprimiendoB >= cantImpresorasB) {
            System.out.println("Soy " + Thread.currentThread().getName() + " no puedo imprimir.");
            this.wait();
        }
        imprimiendoB++;
        System.out.println("Soy " + Thread.currentThread().getName() + " estoy imprimiendo en B.");

    }

    public synchronized void terminaImprimirA() {
        //Deja de imprimir y notifica
        System.out.println(Thread.currentThread().getName() + " terminó de imprimir en la impresora A");
        imprimiendoA--;
        this.notifyAll();
    }

    public synchronized void terminaImprimirB() {
        //Deja de imprimir y notifica
        System.out.println(Thread.currentThread().getName() + " terminó de imprimir en la impresora B");
        imprimiendoB--;
        this.notifyAll();
    }

    public synchronized boolean estaLibreImpresoraA() {

        boolean permisoImpresoraA = false;

        if (imprimiendoA <= imprimiendoB) {

            permisoImpresoraA = true;

        }

        return permisoImpresoraA;

    }

}
