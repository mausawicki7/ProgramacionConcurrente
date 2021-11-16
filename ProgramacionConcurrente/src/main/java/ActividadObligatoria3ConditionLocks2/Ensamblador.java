/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
 */
package ActividadObligatoria3ConditionLocks2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ensamblador implements Runnable {

    private Carpinteria carpinteria;
    private String nombre;

    public Ensamblador(Carpinteria carpinteria, String nombre) {
        this.carpinteria = carpinteria;
        this.nombre = nombre;
    }

    public void run() {
        try {
            System.out.println(nombre+": Intento ensamblar las partes");
            carpinteria.ensamblar(nombre);
            System.out.println(nombre+": Terminé de ensamblar");
        } catch (InterruptedException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
