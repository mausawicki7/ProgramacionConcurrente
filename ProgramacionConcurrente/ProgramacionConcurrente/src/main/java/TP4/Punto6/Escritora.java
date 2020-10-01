/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mausa
 */
public class Escritora {

    private int valor;
    private Semaphore semaforoA = new Semaphore(1);
    private Semaphore semaforoB = new Semaphore(0);
    private Semaphore semaforoC = new Semaphore(0);
    
    public Escritora(int unValor){
        this.valor = unValor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void imprimirA(int cantVeces) {
        try {
            semaforoA.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 1; i < cantVeces; i++) {
            System.out.print("A");
        }
        semaforoB.release();

    }

    public void imprimirB(int cantVeces) {
        try {
            semaforoB.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i < cantVeces; i++) {
            System.out.print("B");
        }
        semaforoC.release();
    }

    public void imprimirC(int cantVeces) {
        try {
            semaforoC.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i < cantVeces; i++) {
            System.out.print("C");
        }
        semaforoA.release();
    }

}
