/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author mausa
 */
public class TestLista {
    public static void main(String [] args){
        Lista lista1 = new Lista();
        Cola cola1 = new Cola();
        
        cola1.poner('a');
        cola1.poner('b');
        cola1.poner('c');
        cola1.poner('d');
        cola1.poner('e');
        cola1.poner('f');
        cola1.poner('g');
        cola1.poner('h');
        cola1.poner('i');
        cola1.poner('j');
        cola1.poner('k');
        
        
        
        
        System.out.println("Cola original:");
        System.out.println(cola1.toString());
        System.out.println("Lsta salida:");
        lista1 = lista1.generarSecuencia(cola1, 3);
        System.out.println(lista1);        

    }
    
}
