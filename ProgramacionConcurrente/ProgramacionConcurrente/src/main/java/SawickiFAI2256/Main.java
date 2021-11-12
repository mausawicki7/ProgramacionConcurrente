/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SawickiFAI2256;

/**
 *
 * @author Mauricio Sawicki FAI-2256
 */
public class Main {

    public static void main(String[] args) {
       // TorreControl unaTorre = new TorreControl("Delta21");

        Thread[] hiloAvion = new Thread[15];

        for (int i = 0; i < hiloAvion.length; i++) {
           // Avion unAvion = new Avion(unaTorre, "Avion " + i);
           // hiloAvion[i] = new Thread(unAvion);
            hiloAvion[i].start();
        }
    }
}
