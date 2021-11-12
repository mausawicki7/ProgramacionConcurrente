/*
 * Mauricio Sawicki
 */
package TP5.LosPollosHermanos;

/**
 *
 * @author mausa
 */
public class Mozo implements Runnable {
    
    private Confiteria miConfiteria;
    private int cuantos;
    
    public Mozo(Confiteria c){
        this.miConfiteria = c;
    }

    @Override
    public void run() {
        while (true) {
            miConfiteria.esperarEmpleado();
            miConfiteria.servirComida();
            this.atender();
            miConfiteria.terminarAtencion();
            cuantos++;
            System.out.println("Soy el Mozo hoy atendí a " + cuantos + " empleados.");
        }

    }

    public boolean atender() {
        boolean atendiendo = true;
        System.out.println("Ok, ahi te traigo la carta.");
        try {
            Thread.sleep((int) (Math.random() * 200));
        } catch (InterruptedException e) {
            System.out.println("Me interrumpieron mi trabajo.");
        }
        return atendiendo;
    }

}
