/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioLinkedTransferQueue;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class main {

    public static void main(String[] args) {

        int cantLugares = 10;
        int cantAutos = 20;
        Rio rio = new Rio(cantLugares);
        String nombre = "Autito ";

        Coordinador c = new Coordinador(rio);
        Thread coord = new Thread(c, "COORDINADOR");
        coord.start();
        
        Transbordador t = new Transbordador(rio);
        Thread trans = new Thread(t, "TRANSBORDADOR");
        trans.start();

        for (int i = 1; i <= cantAutos; i++) {
            Auto a = new Auto(rio, nombre+i);
            Thread auto = new Thread(a, "AUTO " + i);
            auto.start();
        }

    }
}
