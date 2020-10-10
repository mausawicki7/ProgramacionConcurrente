/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto13;

/**
 *
 * @author mausa
 */
public class Empleado extends Persona implements Runnable{
    
    private Confiteria unaConfiteria;

    public Empleado(String nom, Confiteria conf) {
        super(nom);
        this.unaConfiteria = conf;
    } 
    
    private void caminarHastaConfiteria(){
        System.out.println("Soy "+nombre+" estoy yendo a la cafeteria."); 
         try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {
        }
    }
    
 
    @Override
    public void run() {
        this.caminarHastaConfiteria();
        if (unaConfiteria.entrar(nombre)) {
            unaConfiteria.solicitarAtencion(nombre);
            System.out.println("Soy " + nombre + " ya me atendieron, me voy..");
            unaConfiteria.salir(nombre);

        }
    }
    
 
}
