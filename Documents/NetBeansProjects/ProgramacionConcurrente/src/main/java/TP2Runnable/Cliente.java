/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2Runnable;

import TP2Thread.Recurso;


/**
 *
 * @author mausa
 */
public class Cliente implements Runnable {
    public void run(){
        System.out.println("soy " + Thread.currentThread().getName());
        Recurso.uso();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }

}