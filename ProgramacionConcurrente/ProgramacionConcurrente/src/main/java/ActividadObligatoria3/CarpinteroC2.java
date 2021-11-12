/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
*/
package ActividadObligatoria3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CarpinteroC2 implements Runnable {

    private Carpinteria carpinteria;
    private String nombre;
    
    public CarpinteroC2(Carpinteria unaCarpinteria, String unNombre){
    this.carpinteria = unaCarpinteria;  
    this.nombre = unNombre;
}

    @Override
    public void run() {
       // try {
            
            carpinteria.fabricarParte2(nombre);
            System.out.println(nombre + ": comienzo a fabricar la Parte 2");
            this.fabricar();
      //      carpinteria.terminarParte2(nombre);
            System.out.println(nombre + ": terminé de fabricar la Parte 2");
            
       // } catch (InterruptedException ex) {
     //       Logger.getLogger(CarpinteroC1.class.getName()).log(Level.SEVERE, null, ex);
       // }
    }
    
    public void fabricar(){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarpinteroC1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
