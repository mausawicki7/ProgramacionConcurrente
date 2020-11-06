/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.TrenTuristico;

/**
 *
 * @author mausa
 */
public class ControlTren implements Runnable{
    
        Tren t1;

    public ControlTren(Tren t1) {
        this.t1 = t1;
    }
    
    
    public void run(){
    
        while (true) {
            
            t1.empezarViaje();
            t1.simularViaje();
            t1.terminarViaje();
            
            
            
        }
    
    
    
    }
        
    
    
    
    
}
