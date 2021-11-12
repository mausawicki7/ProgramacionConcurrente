/*
 * Mauricio Sawicki
 */
package PrimerParcial.CentroHomoterapia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Donante implements Runnable {

    private String nombre;
    private Centro centro;

    public Donante(String unNombre, Centro unCentro) {
        this.nombre = unNombre;
        this.centro = unCentro;
    }

    public void run() {

        try {
            this.marcarNumero(nombre);
            centro.llamarAlCentro(nombre);
            this.irAlCentro(nombre);
            if (centro.entrar(nombre)) {
                centro.entrevistaMedica(nombre);
                centro.extraccionSangre(nombre);
                this.mirarParaOtroLado(nombre);
                centro.certificadoDonacion(nombre);
                this.desayunar(nombre);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Donante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void marcarNumero(String nombre) {
        System.out.println(nombre + ": estoy marcando el numero para contactar al centro de homoterapia.");
        try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException ex) {
            Logger.getLogger(Donante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void irAlCentro(String nombre) {
        System.out.println(nombre + ": estoy marcando el numero para contactar al centro de homoterapia.");
        try {
            Thread.sleep((int) (Math.random() * 500));
        } catch (InterruptedException ex) {
            Logger.getLogger(Donante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mirarParaOtroLado(String nombre) {
        System.out.println(nombre + ": Me estan por pinchar, mejor mira para otro lado :(");
        try {
            Thread.sleep((int) (Math.random() * 2000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Donante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desayunar(String nombre) {
        try {
            System.out.println(nombre + " está desayunando... Y se fue!");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Donante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
