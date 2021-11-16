/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto4;

/**
 *
 * @author mausa
 */

public class Surtidor {

    public synchronized int cargarNafta(int carga) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" cargando nafta...");
        Thread.sleep(carga*100);
        System.out.println(Thread.currentThread().getName()+" terminó de cargar! Estuvo en el surtidor "+(carga)+" segundos.");
        return carga;
    }
    
    
}

