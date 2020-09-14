/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */

public class Auto extends Vehiculo implements Runnable {

    private int carga;
    private Surtidor surt;

    public Auto(int kmParaAndar, int c, Surtidor surt) {
        super(kmParaAndar);
        carga= c;
        this.surt=surt;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getKmParaAndar() {
        return kmParaAndar;
    }

    public void setKmParaAndar(int kmParaAndar) {
        this.kmParaAndar = kmParaAndar;
    }
    
    
    public void run(){
        for(int i=0; i<= 2;i++){
            System.out.println(Thread.currentThread().getName()+" comienza a andar.");
            int km= this.kmParaAndar;
            for(int j=km; j>0; j--){
                this.kmParaAndar--;
            }
            System.out.println(Thread.currentThread().getName()+" se quedó sin nafta. Recorrió "+km+" kilometros");
            try {
                this.kmParaAndar= surt.cargarNafta(carga);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
            
        }
    }
}
