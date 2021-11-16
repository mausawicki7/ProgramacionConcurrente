/*
 * Mauricio Sawicki
 */
package TP6.Pasteleria;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {

        int capacidadMostrador = 5;
        int pesoMaximoCaja = 20;
        int pesoA = 3;
        int pesoB = 5;
        int pesoC = 2;
        int cantBrazos = 1;
        int cantEmpaquetadores = 2;

        Pasteleria pasteleria = new Pasteleria(pesoMaximoCaja, capacidadMostrador);

        Horno h = new Horno(pasteleria, pesoA);
        Thread hornoA = new Thread(h, "Horno A ");
        Horno h1 = new Horno(pasteleria, pesoB);
        Thread hornoB = new Thread(h1, "Horno B");
        Horno h2 = new Horno(pasteleria, pesoC);
        Thread hornoC = new Thread(h2, "Horno C");

        hornoA.start();
        hornoB.start();
        hornoC.start();

        for (int i = 1; i <= cantEmpaquetadores; i++) {
            Empaquetador p = new Empaquetador(pasteleria);
            Thread empaquetador = new Thread(p, "Empaquetador " + i);
            empaquetador.start();

        }

        for (int i = 1; i <= cantBrazos; i++) {
            Brazo b = new Brazo(pasteleria);
            Thread brazo = new Thread(b, "Brazo " + i);
            brazo.start();

        }

    }

}
