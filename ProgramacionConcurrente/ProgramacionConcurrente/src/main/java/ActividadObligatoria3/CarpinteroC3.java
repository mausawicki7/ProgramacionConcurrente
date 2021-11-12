/*
 * Actividad Obligatoria Programacion Concurrente
   GRUPO 7
   Sawicki Mauricio, Vergara Mariano, Alvarez Percy
*/
package ActividadObligatoria3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CarpinteroC3 implements Runnable {

    private Carpinteria carpinteria;
    private String nombre;
    
    public CarpinteroC3(Carpinteria unaCarpinteria, String unNombre){
    this.carpinteria = unaCarpinteria;  
    this.nombre = unNombre;
}

    @Override
    public void run() {
        //try {
            
            carpinteria.fabricarParte3(nombre);
            System.out.println(nombre + ": comienzo a fabricar la Parte 3");
            this.fabricar();
           // carpinteria.terminarParte3(nombre);
            System.out.println(nombre + ": terminé de fabricar la Parte 3");
            
       // } catch (InterruptedException ex) {
        //    Logger.getLogger(CarpinteroC1.class.getName()).log(Level.SEVERE, null, ex);
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
