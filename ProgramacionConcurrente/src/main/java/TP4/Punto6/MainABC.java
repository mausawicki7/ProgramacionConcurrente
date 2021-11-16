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

    public static void main(String[] args) {
        
        Escritora esc = new Escritora(5);  //Cantidad de veces que se van a imprimir
        ClaseA a = new ClaseA(2, esc);
        ClaseB b = new ClaseB(3, esc);
        ClaseC c = new ClaseC(4, esc);

        Thread [] misHilos = new Thread[3];
        misHilos[0] = new Thread(a);
        misHilos[1] = new Thread(b);
        misHilos[2] = new Thread(c);
        
        for(int i=0; i<misHilos.length; i++){
            misHilos[i].start();
        }
    }
}
