/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Runnable;

/**
 *
 * @author mausa
 */
public class ThreadTesting {
    
  public static void main(String[] args) {
      
        MiEjecucion e1 = new MiEjecucion();
        Thread miHilo = new Thread(e1);
     //   System.out.println("se esta ejecutando primero: "+Thread.currentThread().getName());
        miHilo.start();
        try {
            miHilo.join();
        } catch (InterruptedException ex) {
        
        }
        System.out.println("En el main");
    }
}
