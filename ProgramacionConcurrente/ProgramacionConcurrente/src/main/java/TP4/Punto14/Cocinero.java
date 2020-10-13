/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto14;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Cocinero extends Persona implements Runnable {

    Confiteria unaConfiteria;

    public Cocinero(String nom, Confiteria conf) {
        super(nom);
        this.unaConfiteria = conf;
    }


   public boolean atenderC() {
        boolean atendiendo = true;
        try {
            System.out.println("Estoy cocinando, no me tardo mucho");
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {
             Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, e);
        }
        return atendiendo;
    }
    
    
     public void run() {
        int cuantos = 0;
        System.out.println("Soy el cocinero "+nombre);
        
        //Espera activa
        while(true){
            unaConfiteria.cocineroEsperaEmpleado(nombre);
            this.atenderC();
            unaConfiteria.terminarAtencionCocinero();
            cuantos++;
            System.out.println("Soy el cocinero atendi a "+cuantos+" empleados.");
        }
    }

}
