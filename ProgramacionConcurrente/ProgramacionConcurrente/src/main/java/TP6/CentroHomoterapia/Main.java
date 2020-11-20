/*
 * Mauricio Sawicki
 */
package TP6.CentroHomoterapia;

import java.util.Random;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int camillas = 4;
        int revistas = 3;
        int sillas = 6;
        int capacidad = 10;
        int cantPacientes = 25;
        Random r = new Random();
        int eleccion = r.nextInt(1);

        Centro unCentro = new Centro(camillas, revistas, sillas, capacidad);

        for (int i = 0; i <= cantPacientes; i++) {

            Paciente p1 = new Paciente(unCentro, eleccion);
            Thread hiloPaciente = new Thread(p1, "Paciente " + i);
            hiloPaciente.start();

        }

    }
}
