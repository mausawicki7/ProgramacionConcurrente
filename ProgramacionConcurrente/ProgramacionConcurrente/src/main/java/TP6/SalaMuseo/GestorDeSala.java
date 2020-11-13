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
                System.out.println("Soy "+Thread.currentThread().getName()+" no pude entrar, me quedo esperando.");
                this.wait();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantActualPersonas++;
         System.out.println(Thread.currentThread().getName()+" entró.");
    }
    //Personas que son jubiladas ejecutan este metodo
    public synchronized void entrarSalaJubilado() {       
        while (cantActualPersonas >= cantMaxPersonas) {
            try {
                jubiladosEnEspera++;
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
    
    public void recorrerMuseo(){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void notificarTemperatura(int tempActual){
        if(tempActual > tempUmbral){
            cantMaxPersonas = 5;
            System.out.println("Ahora la capacidad del museo es de: "+cantMaxPersonas);
        }
        
    }

}
