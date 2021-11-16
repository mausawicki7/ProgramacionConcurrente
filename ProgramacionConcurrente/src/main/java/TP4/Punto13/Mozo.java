/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto13;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Mozo extends Persona implements Runnable {

    Confiteria unaConfiteria;

    public Mozo(String nom, Confiteria conf) {
        super(nom);
        this.unaConfiteria = conf;
    }

    public boolean atender() {
        boolean atendiendo = true;
        System.out.println("Hola buenos dias, aqui tiene el menú.");
        try {
            Thread.sleep((int) (Math.random() * 200));
        } catch (InterruptedException e) {
             Logger.getLogger(Confiteria.class.getName()).log(Level.SEVERE, null, e);
        }
        return atendiendo;
    }

    public void run() {
        int cuantos = 0;
        System.out.println("Soy el mozo "+nombre);
        
        //Espera activa
        while(true){
            unaConfiteria.esperarEmpleado(nombre);
            this.atender();        
            unaConfiteria.terminaDeComer();
            cuantos++;
            System.out.println("Atendi a "+cuantos+" empleados.");
        }
    }

}
