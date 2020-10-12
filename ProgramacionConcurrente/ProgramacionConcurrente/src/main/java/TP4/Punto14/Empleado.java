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
public class Empleado extends Persona implements Runnable{
    
    private Confiteria unaConfiteria;
    private int eleccion;

    public Empleado(String nom, Confiteria conf, int elec) {
        super(nom);
        this.unaConfiteria = conf;
        this.eleccion = elec;
        
    } 
    
    private void caminarHastaConfiteria(){
        System.out.println("Soy "+nombre+" estoy yendo a la cafeteria."); 
         try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {
        }
    }
    
    public void beber(String nombre){
        System.out.println("Soy "+nombre+" estoy bebiendo.");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void comer(String nombre){
        System.out.println("Soy "+nombre+" estoy comiendo.");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        this.caminarHastaConfiteria();
        if (unaConfiteria.entrar(nombre)) {
           //Si hay sillas disponibles el empleado se sienta y espera ser atendido
           //0 = Empleado pide bebida
           //1 = Empleado pide comida
           //2 = Empleado pide bebida y comida.
           switch(this.eleccion){
               case 0:
                   unaConfiteria.pedirBebida(nombre);
                   this.beber(nombre);
                   break;
               case 1:
                   unaConfiteria.pedirComida(nombre);
                   this.comer(nombre);
                   break;
               case 2:
                   unaConfiteria.pedirComidaYBebida(nombre);
                   this.beber(nombre);
                   this.comer(nombre);
                   break;
           }
           System.out.println("Soy " + nombre + " ya me atendieron, me voy..");
           unaConfiteria.salir(nombre);

        }
    }
    
 
}
