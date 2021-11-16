/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto12;

/**
 *
 * @author mausa
 */
public class Hamster implements Runnable {

    String nombre;
    Jaula jaula;

    public Hamster(String nombre, Jaula j) {
        this.nombre = nombre;
        this.jaula = j;
    }
    
    public void run (){
        jaula.usarRueda(nombre);
        jaula.comer(nombre);
        jaula.descansarEnHamaca(nombre);
        
    }
    
    
    
}
