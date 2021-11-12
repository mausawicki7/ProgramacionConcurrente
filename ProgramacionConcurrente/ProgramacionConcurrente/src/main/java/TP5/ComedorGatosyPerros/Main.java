/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.ComedorGatosyPerros;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        /*Funciona para (gatos y perros > platos) y para (gatos y perros  = platos)
        y también para (gatos o perros < platos) */
        int cantGatos = 10, cantPerros = 2, cantPlatos = 4 ;
       
        Comedor c1 = new Comedor(cantPlatos, cantGatos, cantPerros);

        for (int i = 0; i < cantGatos; i++) {

            Gato g = new Gato(c1);
            Thread t1 = new Thread(g, " Gato " + i);
            t1.start();
        }

        for (int i = 0; i < cantPerros; i++) {
            Perro p = new Perro(c1);
            Thread perro = new Thread(p, " Perro " + i);
            perro.start();

        }

    }
}