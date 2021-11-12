/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Jaula {

 private ReentrantLock lockHamaca = new ReentrantLock();
 private ReentrantLock lockPlato = new ReentrantLock();
 private ReentrantLock lockRueda = new ReentrantLock();


    
    public Jaula() {

    }

    public void usarRueda(String nombreHamster) {
     try {
        lockRueda.lock();
        System.out.println("\033[34mEl hamster "+nombreHamster+" se esta ejercitando en la rueda.");
        Thread.sleep(1000);
     } catch (InterruptedException ex) {
         Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
     }
     finally{
         System.out.println("El hamster "+nombreHamster+" termino de ejercitarse.");
         lockRueda.unlock();
     }
     
    }
    
    public void comer(String nombreHamster) {
     try {
        lockPlato.lock();
        System.out.println("El hamster "+nombreHamster+" esta comiendo.");
        Thread.sleep(1000);
     } catch (InterruptedException ex) {
         Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
     }
     finally{
         System.out.println("El hamster "+nombreHamster+" termino de comer.");
         lockPlato.unlock();
     }
    }
     
     public void descansarEnHamaca(String nombreHamster) {
     try {
        lockHamaca.lock();
        System.out.println("El hamster "+nombreHamster+" esta descansando en la hamaca.");
        Thread.sleep(1000);
     } catch (InterruptedException ex) {
         Logger.getLogger(Jaula.class.getName()).log(Level.SEVERE, null, ex);
     }
     finally{
         System.out.println("El hamster "+nombreHamster+" termino su descanso.");
         lockHamaca.unlock();
     }
     
    }

     
 


}
