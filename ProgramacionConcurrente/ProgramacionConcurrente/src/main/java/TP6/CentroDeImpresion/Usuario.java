/*
 * Mauricio Sawicki
 */
package TP6.CentroDeImpresion;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Usuario implements Runnable {

    private CentroDeImpresion centro;
    private int userID;
    private Random r;

    public Usuario(int userID, CentroDeImpresion unCentro) {
        this.centro = unCentro;
        this.userID = userID;
        r = new Random();
    }

    public void generarTrabajo() {
        System.out.println("Soy el usuario " + userID + " estoy generando un trabajo.");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            int eleccionImpresora = (r.nextInt(3) + 1);
            this.generarTrabajo();
            switch (eleccionImpresora) {
                case 0:
                    usarImpresoraA();
                    break;
                case 1:
                    usarImpresoraB();
                    break;
                case 2:
                    Thread.currentThread().setName("Usuario general");
                    if (centro.estaLibreImpresoraA()) {
                        usarImpresoraA();
                    } else {
                        usarImpresoraB();
                    }
                    break;
            }

        }
    }

    private void usarImpresoraA() {

        try {
            centro.imprimirA();
            this.esperarImpresion();
            centro.terminaImprimirA();
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void usarImpresoraB() {

        try {
            centro.imprimirB();
            this.esperarImpresion();
            centro.terminaImprimirB();
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void esperarImpresion() {

        try {
            System.out.println(Thread.currentThread().getName() + " esperando que la impresora termine..");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
