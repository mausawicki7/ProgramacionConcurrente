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
public class Test {
    public static void main(String[] args) {
        Confiteria unaConfiteria = new Confiteria();
        Mozo mozo = new Mozo(unaConfiteria);
        Thread hiloMozo = new Thread(mozo);
        hiloMozo.start();
        Thread[] hiloEmpleados = new Thread[10];
        
        for (int i = 0; i < hiloEmpleados.length; i++) {
            Empleado unEmpleado = new Empleado("Empleado " + i, unaConfiteria);
            hiloEmpleados[i] = new Thread(unEmpleado);
            hiloEmpleados[i].start();
        }

        try {
            Thread.sleep((int) (Math.random() * 500));

        } catch (InterruptedException ex) {
            Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
