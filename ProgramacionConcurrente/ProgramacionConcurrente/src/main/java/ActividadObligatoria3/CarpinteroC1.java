/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
*/
package ActividadObligatoria3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CarpinteroC1 implements Runnable {
    
    private Carpinteria carpinteria;
    private String nombre;
    
    public CarpinteroC1(Carpinteria unaCarpinteria, String unNombre){
    this.carpinteria = unaCarpinteria;  
    this.nombre = unNombre;
}

    @Override
    public void run() {
        try {
            
            carpinteria.fabricarParte1(nombre);
            System.out.println(nombre + ": comienzo a fabricar la Parte 1");
            this.fabricar();
            carpinteria.terminarParte1(nombre);
            System.out.println(nombre + ": terminé de fabricar la Parte 1");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(CarpinteroC1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fabricar(){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarpinteroC1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
