/*
 * Mauricio Sawicki
 */
package TP6.SalaMuseo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class GestorDeSala {

    private int cantMaxPersonas, cantActualPersonas=0, tempUmbral=30;
    private int jubiladosEnEspera=0;

    public GestorDeSala(int cantMax) {
        this.cantMaxPersonas = cantMax;
    } 
    
    //Personas que no son jubiladas ejecutan este metodo
    public synchronized void entrarSala() {      
        while (jubiladosEnEspera > 0 || cantActualPersonas >= cantMaxPersonas) {
            try {
                System.out.println("Soy "+Thread.currentThread().getName()+" hay jubilados en espera o esta lleno, no pude entrar, me quedo esperando.");
                this.wait();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cantActualPersonas++;
        System.out.println("capActual:" +cantActualPersonas);
         System.out.println(Thread.currentThread().getName()+" entró.");
    }
    
    //Personas que son jubiladas ejecutan este metodo
    public synchronized void entrarSalaJubilado() {       
        while (cantActualPersonas >= cantMaxPersonas) {
            try {
                jubiladosEnEspera++;
                System.out.println("Jubilados en espera: "+jubiladosEnEspera);
                System.out.println("Soy "+Thread.currentThread().getName()+" no pude entrar, me quedo esperando.");
                this.wait();
                
                jubiladosEnEspera--;
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        cantActualPersonas++;
         System.out.println(Thread.currentThread().getName()+" entró.");

    }

    public synchronized void salirSala() {
        cantActualPersonas--;
        System.out.println(Thread.currentThread().getName()+" salió.");
        this.notifyAll();
    }
    
    
    public void notificarTemperatura(int tempActual){
        if(tempActual > tempUmbral){ //si se pasa de 35 grados la temp, limitar la capacidad a cantMaxPersonas
            cantMaxPersonas = 35;
            System.out.println("Temperatura muy alta! Baja la capacidad del museo es de: "+cantMaxPersonas);
        }
        
    }

}
