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
public class ClaseA implements Runnable {

    private int cant;
    private Escritora i;

    public ClaseA(int cant, Escritora i) {
        this.cant = cant;
        this.i = i;
    }
    
    
    @Override
    public void run() {
        int cantI = i.getValor();
        for(int x=0; x<cantI; x++){
            i.imprimirA(cant);
        }
       
    }
    
}
