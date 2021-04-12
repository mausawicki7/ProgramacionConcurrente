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
public class MainABC {
    public static void main(String [] args){
        Escritora i = new Escritora(5);
        ClaseA a = new ClaseA(i, 1);
        ClaseB b = new ClaseB(i, 2);
        ClaseC c = new ClaseC(i, 3);
        
        Thread[] hiloLetras = new Thread[3];
        hiloLetras[0] = new Thread(a);
        hiloLetras[1] = new Thread(b);
        hiloLetras[2] = new Thread(c);
        
        for(int x=0 ; x<=2; x++){
            hiloLetras[x].start();   
        }
    }
    
}
