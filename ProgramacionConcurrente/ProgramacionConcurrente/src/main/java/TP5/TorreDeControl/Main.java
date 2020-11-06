/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP5.TorreDeControl;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int cantAvionesConPrioridad = 5;
        int cantDespegues = 7;
        int cantAterrizajes = 12;
        
        TorreDeControl unaTorre = new TorreDeControl(cantAterrizajes, cantAvionesConPrioridad);

        for (int i = 0; i < cantDespegues; i++) {
            Avion unAvion = new Avion(unaTorre, 2);
            Thread a = new Thread(unAvion, "Avion D-" + i);
            a.start();
        }
        
        for (int i = cantDespegues; i < cantAterrizajes + cantDespegues; i++) {
            Avion unAvion = new Avion(unaTorre,1);
            Thread a = new Thread(unAvion, "Avion A-" + i);
            a.start();
 
        }

    }

}
