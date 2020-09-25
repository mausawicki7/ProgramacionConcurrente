/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP4.Punto6;

/**
 *
 * @author mausa
 */

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escritora {
    private int valor;
    //Semaforo con 1 permiso para que arranque escribiendo A
    private Semaphore semaforoA = new Semaphore(1);
    private Semaphore semaforoB = new Semaphore(0);
    private Semaphore semaforoC = new Semaphore(0);

    public Escritora (int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void imprimirA(int cantVeces){
        
        //Adquirir el permiso del semaforoA
        try {
            semaforoA.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Imprime A cantVeces
        for(int i = 1; i <= cantVeces; i++){
        System.out.print("A");
        }
        //Termina de imprimir la cantidad de veces ingresada por parametro para A
        //Lanza el semaforo B, le da un permiso para ser tomado.
        semaforoB.release();
    }

    public void imprimirB(int cantVeces){
        try {
            semaforoB.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cuando el semaforo B tiene un permiso, se imprime B cantVeces
        for(int i = 1; i <= cantVeces; i++){
        System.out.print("B");
        }
        //Termina de imprimir la cantidad de veces ingresada por parametro para B
        //Lanza el semaforo C, le da un permiso para ser tomado.
        semaforoC.release();
    }
    
    public void imprimirC(int cantVeces){
        try {
            semaforoC.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritora.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 1; i <= cantVeces; i++){
        System.out.print("C");
        }
        semaforoA.release();
    }
           
    
}
