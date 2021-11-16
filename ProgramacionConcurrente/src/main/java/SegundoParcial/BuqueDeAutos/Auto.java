/*
 * Mauricio Sawicki
 */
package SegundoParcial.BuqueDeAutos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Auto implements Runnable {
    
    private Transbordador transbordador;
    private String nombre;
    
    public Auto(Transbordador unTransbordador, String unNombre){
        this.nombre = unNombre;
        this.transbordador = unTransbordador;
    }
    
    public void run(){
        try {
            
            transbordador.subirAlTransbordador(nombre);
            this.simularTiempoEnTransbordador();
            transbordador.bajarDelTransbordador(nombre);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simularTiempoEnTransbordador() throws InterruptedException{
        Thread.sleep(2500);
    }
}
