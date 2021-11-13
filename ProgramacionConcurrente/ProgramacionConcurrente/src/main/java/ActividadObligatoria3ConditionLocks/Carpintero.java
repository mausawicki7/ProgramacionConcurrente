/*
 * Mauricio Sawicki
 */
package ActividadObligatoria3ConditionLocks;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Carpintero implements Runnable {

    private Carpinteria carpinteria;
    private String nombre;
    private int miNumero;

    public Carpintero(Carpinteria unaCarpinteria, String unNombre, int unNumero) {
        this.carpinteria = unaCarpinteria;
        this.nombre = unNombre;
        this.miNumero = unNumero;
    }

    public void run() {
        this.realizarParte();
    }

    private void realizarParte() {
        try {
            switch (miNumero) {
                case 1:
                    System.out.println(nombre + ": Comienzo a fabricar la parte 1");
                    carpinteria.fabricarParte1(nombre);
                    System.out.println(nombre + ": Terminé de fabricar la parte 1");
                    break;

                case 2:
                    System.out.println(nombre + ": Comienzo a fabricar la parte 2");
                    carpinteria.fabricarParte2(nombre);
                    System.out.println(nombre + ": Terminé de fabricar la parte 2");
                    break;

                case 3:
                    System.out.println(nombre + ": Comienzo a fabricar la parte 3");
                    carpinteria.fabricarParte3(nombre);
                    System.out.println(nombre + ": Terminé de fabricar la parte 3");
                    break;

                default:
                    throw new AssertionError();

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Carpintero.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
