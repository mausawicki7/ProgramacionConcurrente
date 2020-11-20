/*
 * Mauricio Sawicki
 */
package TP6.CentroHomoterapia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Paciente implements Runnable {

    private boolean quiereSentarse;
    private boolean seSento;
    private Centro centro;

    public Paciente(Centro centro, int sentarse) {
        this.centro = centro;
        this.quiereSentarse = (sentarse == 1);

    }

    public void run() {

        centro.entrar();

        if (quiereSentarse) {

            seSento = centro.sentarse();

        } else {

            System.out.println(Thread.currentThread().getName() + " se quedó parado");

        }

        centro.tomarRevista();
        this.leerRevista();
        centro.soltarRevista();

        centro.entrarExtraccion(seSento);
        this.esperarExtraccion();
        centro.salirExtraccion();

    }

    private void leerRevista() {

        try {
            System.out.println(Thread.currentThread().getName() + " leyendo una revista..");
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void esperarExtraccion() {

        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
