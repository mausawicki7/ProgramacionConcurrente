/*
 * Mauricio Sawicki
 */
package SegundoParcial.BuqueDeAutos;

/**
 *
 * @author mausa
 */
public class Test {

    public static void main(String[] args) {

        //Cantidad de hilos pasajero.
        int cantAutos = 20;
        //Cantidad de lugares en el transbordador, maximo de autos.
        int cantLugares = 10;

        Transbordador t = new Transbordador(cantLugares);

        Control c = new Control(t);
        Thread control = new Thread(c);
        control.start();

        for (int i = 1;i <= cantAutos; i++) {
            Auto a = new Auto(t, "Auto: "+i);
            Thread hiloAutos = new Thread(a);
            hiloAutos.start();
        }
    }
}
