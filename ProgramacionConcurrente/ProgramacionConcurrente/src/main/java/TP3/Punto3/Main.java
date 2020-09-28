/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP3.Punto3;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
        Organizador org = new Organizador();
        
        //Creo una instancia de la clase Cadena que esta clase implementa la interfaz Runnable
        Cadena uno = new Cadena("A", org);
        //Creo el hilo y le envio como parametro la instancia 
        Thread a = new Thread(uno);

        Cadena dos = new Cadena("BB", org);
        Thread b = new Thread(dos);

        Cadena tres = new Cadena("CCC", org);
        Thread c = new Thread(tres);
        
        a.start();
        b.start();
        c.start();

    }

}