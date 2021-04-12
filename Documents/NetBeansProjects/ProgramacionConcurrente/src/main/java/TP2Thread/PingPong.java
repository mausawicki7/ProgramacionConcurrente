/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Thread;

/**
 *
 * @author mausa
 */
public class PingPong extends Thread {

    private String cadena;
    private int delay;

    public PingPong(String cartel, int cantMS) {
        this.cadena = cartel;
        this.delay = cantMS;
    }

    @Override
    public void run() {
        for (int i = 1; i < delay * 10; i++) {
            System.out.println(cadena + " ");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

            }
     
        }
 
    }

}
