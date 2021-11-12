/*
 * Mauricio Sawicki
 */
package SawickiFAI2256.Ejercicio2;

import TP5.LosPollosHermanos.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Empleado implements Runnable {

    Confiteria laConfiteria;
    String miNombre;

    public Empleado(String nombre, Confiteria c) {
        this.miNombre = nombre;
        this.laConfiteria = c;
    }

    private void caminarHastaConfiteria() {
        System.out.println("Soy " + miNombre + " y estoy caminando hasta la confiteria");
        try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        this.caminarHastaConfiteria();
        if (laConfiteria.entrar(miNombre)) {
            try {
                laConfiteria.empezarAComer(miNombre);
                this.comer();
                laConfiteria.terminarDeComer();
                System.out.println(Thread.currentThread().getName() + ": gracias por la atención, nos vemos!");
                laConfiteria.salir();
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void comer() {
        System.out.println(miNombre + "estoy comiendo...");
        try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {

        }
    }
}
