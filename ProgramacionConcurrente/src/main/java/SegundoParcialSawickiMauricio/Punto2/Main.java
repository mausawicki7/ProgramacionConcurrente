/*
 * Mauricio Sawicki
 */
package SegundoParcialSawickiMauricio.Punto2;

/**
 *
 * @author mausa
 */
public class Main {

    public static void main(String[] args) {
        
        //Cantidad de hilos pasajero.
        int pasajeros = 16;
        //Cantidad de lugares en el carro, maximo de pasajeros.
        int cantLugares = 12;
        //Cantidad de recorridos maximos que va a funcionar la montaña rusa por dia.
        int maxRecorridos = 3;

        MontañaRusa m = new MontañaRusa(cantLugares, maxRecorridos);

        ControlAtraccion c = new ControlAtraccion(m, maxRecorridos);
        Thread control = new Thread(c, "Control: ");
        control.start();
        
        for (int i = 1; i <= pasajeros; i++) {
            Pasajero p = new Pasajero(m);
            Thread hiloPasajero = new Thread(p, "Pasajero " + i);
            hiloPasajero.start();
        }
    }
}
