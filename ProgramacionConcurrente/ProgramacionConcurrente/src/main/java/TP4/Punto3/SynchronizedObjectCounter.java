/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto3;

/**
 *
 * @author mausa
 */
public class SynchronizedObjectCounter {

    private int c = 0;

    public void increment() {
        synchronized ((Integer)c) {
            c++;
        } 
    }

    public void decrement() {
        synchronized (this) {
            c--;
        }
    }

    public int value() {
        return c;
    }
}