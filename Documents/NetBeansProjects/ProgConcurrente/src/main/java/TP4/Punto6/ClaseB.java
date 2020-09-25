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
public class ClaseB implements Runnable{
    
    Escritora x;
    private int cant;

    public ClaseB(Escritora x , int cantVeces) {
        this.x = x;
        this.cant = cantVeces;
    }
    
    @Override
    public void run() {
        int cantVeces = x.getValor();
        for(int i = 0; i < cantVeces; i++){
           x.imprimirA(cant);
        }
    }
    
}
