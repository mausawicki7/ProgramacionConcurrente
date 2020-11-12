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

    private int cantMaxPersonas, cantActualPersonas, tempUmbral=30;
    private boolean jubiladoQuiereEntrar = false;

    public GestorDeSala(int cantMax, int cantActual) {
        this.cantMaxPersonas = cantMax;
        this.cantActualPersonas = cantActual;
    } 
    
    //Personas que no son jubiladas ejecutan este metodo
    public synchronized void entrarSala() {
        while (!jubiladoQuiereEntrar && cantActualPersonas <= cantMaxPersonas) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantMaxPersonas++;
         System.out.println(Thread.currentThread().getName()+" entró.");
    }
    //Personas que son jubiladas ejecutan este metodo
    public synchronized void entrarSalaJubilado() {
        while (cantActualPersonas <= cantMaxPersonas) {
            try {
                jubiladoQuiereEntrar = true;
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorDeSala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jubiladoQuiereEntrar = false;
        cantActualPersonas++;
         System.out.println(Thread.currentThread().getName()+" entró.");

    }

    public synchronized void salirSala() {
        cantActualPersonas--;
        System.out.println(Thread.currentThread().getName()+" salió.");
        this.notifyAll();

    }
    
    public void notificarTemperatura(int tempActual){
        if(tempActual > tempUmbral){
            cantMaxPersonas = 35;
        }
        
    }

}
