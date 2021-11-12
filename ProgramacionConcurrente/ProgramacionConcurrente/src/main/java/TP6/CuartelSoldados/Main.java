/*
 * Mauricio Sawicki
 */
package TP6.CuartelSoldados;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int cantSoldados = 12;
        int cantBandejas = 4;
        int cantDestapadores = 10;
        int cantPostres = 1;
        int cantMostradores = 5;
        
        Cuartel cuartel = new Cuartel(cantMostradores, cantBandejas, cantPostres, cantDestapadores);

        for (int i = 1; i <= cantSoldados; i++) {
            int quierePostre = randomHasta(1);
            int quiereGaseosa = randomHasta(1);

            Soldado s = new Soldado(cuartel, quierePostre, quiereGaseosa);
            Thread soldado = new Thread(s, "Soldado " + i);
            soldado.start();
        }
    }

    public static int randomHasta(int maximo) {
        Random rand = new Random();

        return rand.nextInt(maximo + 1);
    }
}
