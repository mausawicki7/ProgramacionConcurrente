/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConSemaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ensamblador implements Runnable {

    private Carpinteria carpinteria;
    private String nombre;
    
    public void run() {
        try {
            System.out.println(nombre+": Comienzo a ensamblar las partes");
            carpinteria.comenzarEnsamblaje();
            this.ensamblar();
            carpinteria.terminarEnsamblaje();
            System.out.println(nombre+": Terminé de ensamblar");
        } catch (InterruptedException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ensamblar() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
