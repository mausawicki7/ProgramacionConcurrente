/*
 * Mauricio Sawicki
 */
package TP6.Observatorio;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class Main {
        public static void main(String[] args) {
        int capacidadMaxima = 5;
        int capacidadLimitada = 3;
        int cantVisitantes = 30;
        int cantMantenimiento = 7;
        int cantInvestigadores = 5;

        Random r = new Random();

        Sala obs = new Sala(capacidadMaxima, capacidadLimitada);

        for (int i = 1; i <= cantVisitantes; i++) {
            Visitante v = new Visitante(obs, r.nextInt(5));
            Thread visitante = new Thread(v, "Visitante " + i);
            visitante.start();
        }

        for (int i = 1; i <= cantMantenimiento; i++) {
            PersonalMantenimiento m = new PersonalMantenimiento(obs);
            Thread mantenimiento = new Thread(m, "Mantenimiento " + i);
            mantenimiento.start();

        }

        for (int i = 1; i <= cantInvestigadores; i++) {
            Investigador in = new Investigador(obs);
            Thread investigador = new Thread(in, "Investigador " + i);
            investigador.start();

        }

    }
}
